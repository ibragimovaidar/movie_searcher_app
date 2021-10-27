package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieGenreRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MovieGenreRepositoryImpl implements MovieGenreRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieGenreRepositoryImpl.class);

	private final Function<ResultSet, MovieGenre> movieGenreResultSetExtractor = (ResultSet rs) -> {
		try {
			if (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				return new MovieGenre(id, name);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	};

	private final Function<ResultSet, List<MovieGenre>> movieGenreListResultSetExtractor = (ResultSet rs) -> {
		List<MovieGenre> movieGenreList = new ArrayList<>();
		try {
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				MovieGenre movieGenre = new MovieGenre(id, name);
				movieGenreList.add(movieGenre);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			LOGGER.error("", throwables);
		}
		return movieGenreList;
	};

	//language=SQL
	private static final String SQL_FIND_ALL = "SELECT id, name FROM movie_genre";

	@Override
	public List<MovieGenre> findAll() {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)
		) {
			ResultSet resultSet = statement.executeQuery();
			return movieGenreListResultSetExtractor.apply(resultSet);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
			return new ArrayList<>();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, name FROM movie_genre " +
			"WHERE id = ?";


	@Override
	public Optional<MovieGenre> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)
		) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(movieGenreResultSetExtractor.apply(resultSet));
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_NAME = "SELECT id, name FROM movie_genre " +
			"WHERE name = ?";

	@Override
	public Optional<MovieGenre> findByName(String name) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME)
		){
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(movieGenreResultSetExtractor.apply(resultSet));
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO movie_genre(name) " +
			"VALUES (?)";

	@Override
	public MovieGenre save(MovieGenre movieGenre) {
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
		) {
			statement.setString(1, movieGenre.getName());
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()){
					movieGenre.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Generated key extraction error");
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			LOGGER.error("", throwables);
		}
		return movieGenre;
	}
}
