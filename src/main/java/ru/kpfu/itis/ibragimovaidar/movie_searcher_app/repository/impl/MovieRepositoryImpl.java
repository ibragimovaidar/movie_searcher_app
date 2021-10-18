package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Person;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ImageRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieGenreRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.PersonRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MovieRepositoryImpl implements MovieRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(MovieRepositoryImpl.class);

	private ImageRepository imageRepository;
	private PersonRepository personRepository;
	private MovieGenreRepository movieGenreRepository;

	public MovieRepositoryImpl(ImageRepository imageRepository,
							   PersonRepository personRepository,
							   MovieGenreRepository movieGenreRepository) {
		this.imageRepository = imageRepository;
		this.personRepository = personRepository;
		this.movieGenreRepository = movieGenreRepository;
	}

	private final Function<ResultSet, Movie> movieResultSetExtractor = (ResultSet rs) -> {
		Movie movie = null;
		try {
			if (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				LocalDate dateOfRelease = rs.getDate("date_of_release").toLocalDate();
				int averageRating = rs.getInt("averageRating");
				String description = rs.getString("description");
				MovieGenre movieGenre = movieGenreRepository.findById(rs.getInt("movie_genre_id")).orElse(null);
				ImageMetadata imageMetadata = imageRepository.findById(rs.getInt("image_matadata_id")).orElse(null);
				List<Person> participants = personRepository.findByMovieId(id);
				movie = new Movie(id, name, dateOfRelease, country, averageRating, movieGenre, participants, description, imageMetadata);
			}
		} catch (SQLException e) {
			LOGGER.error("movieResultSetExtractor error ", e);
		}
		return movie;
	};

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, name, date_of_release, country, average_rating, " +
			"description, movie_genre_id, image_metadata_id " +
			"FROM movie WHERE id = ?";

	@Override
	public Optional<Movie> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)
		) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(movieResultSetExtractor.apply(resultSet));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return Optional.empty();
		}
	}


	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO movie(name, date_of_release, country, average_rating, " +
			"description, movie_genre_id, image_metadata_id) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?)";

	@Override
	public Movie save(Movie movie) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
		) {
			statement.setString(1, movie.getName());
			statement.setDate(2, Date.valueOf(movie.getDateOfRelease()));
			statement.setString(3, movie.getCountry());
			statement.setInt(4, 0);
			statement.setString(5, movie.getDescription());
			statement.setInt(6, movie.getMovieGenre().getId());
			statement.setInt(7, movie.getImageMetadata().getId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows < 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()){
					movie.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Generated key extraction error");
				}
			}
			saveParticipators(movie);
		} catch (SQLException throwables) {
			LOGGER.error("Save error", throwables);
			throwables.printStackTrace();
		}
		return movie;
	}

	//language=SQL
	private static final String SQL_SAVE_PARTICIPATORS = "INSERT INTO movies_participators(movie_id, person_id) " +
			"VALUES (?, ?)";

	private void saveParticipators(Movie movie){
		for (Person person: movie.getParticipants()){
			try (Connection connection = ConnectionManager.getConnection();
				 PreparedStatement statement = connection.prepareStatement(SQL_SAVE_PARTICIPATORS)
			) {
				statement.setInt(1, movie.getId());
				statement.setInt(2, person.getId());
				int affectedRows = statement.executeUpdate();
				if (affectedRows < 1){
					throw new SQLException("Save error");
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}
