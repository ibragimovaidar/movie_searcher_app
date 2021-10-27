package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieCommentary;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieCommentaryRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieCommentaryRepositoryImpl implements MovieCommentaryRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieCommentaryRepositoryImpl.class);

	private final UserRepository userRepository;

	public MovieCommentaryRepositoryImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, text, rating, created_at, msa_user_id, movie_id " +
			"FROM movie_commentary WHERE id = ?";

	@Override
	public Optional<MovieCommentary> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)
		) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(extractMovieCommentary(resultSet));
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_MOVIE_ID = "SELECT id, text, rating, created_at, msa_user_id, movie_id " +
			"FROM movie_commentary WHERE movie_id = ?";

	@Override
	public List<MovieCommentary> findByMovieId(Integer movieId) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MOVIE_ID)
		) {
			statement.setInt(1, movieId);
			ResultSet resultSet = statement.executeQuery();
			return extractMovieCommentaryList(resultSet);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
			return new ArrayList<>();
		}
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO movie_commentary(text, rating, " +
			"created_at, msa_user_id, movie_id) " +
			"VALUES (?, ?, ?, ?, ?)";

	@Override
	public MovieCommentary save(MovieCommentary movieCommentary, Integer movieId) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
		) {
			statement.setString(1, movieCommentary.getText());
			statement.setInt(2, movieCommentary.getRating());
			statement.setTimestamp(3, Timestamp.valueOf(movieCommentary.getCreatedAt()));
			statement.setInt(4, movieCommentary.getOwner().getId());
			statement.setInt(5, movieId);
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				movieCommentary.setId(generatedKeys.getInt("id"));
			}
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return movieCommentary;
	}

	private MovieCommentary extractMovieCommentary(ResultSet rs){
		MovieCommentary movieCommentary = null;
		try {
			if (rs.next()){
				movieCommentary = extractRow(rs);
			}
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return movieCommentary;
	}

	private List<MovieCommentary> extractMovieCommentaryList(ResultSet rs){
		List<MovieCommentary> movieCommentaries = new ArrayList<>();
		try {
			while (rs.next()){
				movieCommentaries.add(extractRow(rs));
			}
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return movieCommentaries;
	}

	private MovieCommentary extractRow(ResultSet rs){
		MovieCommentary movieCommentary = null;
		try {
			int id = rs.getInt("id");
			String text = rs.getString("text");
			int rating = rs.getInt("rating");
			LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
			User msa_user_id = userRepository.findById(rs.getInt("msa_user_id")).orElse(null);
			movieCommentary = new MovieCommentary(id, text, createdAt, rating, msa_user_id);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return movieCommentary;
	}
}
