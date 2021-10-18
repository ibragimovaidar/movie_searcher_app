package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Review;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ReviewRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ReviewRepositoryImpl implements ReviewRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewRepositoryImpl.class);

	private MovieRepository movieRepository;

	private final Function<ResultSet, List<Review>> reviewListResultSetExtractor = (ResultSet rs) -> {
		List<Review> reviews = new ArrayList<>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int rating = rs.getInt("rating");
				String description = rs.getString("description");
				Movie movie = movieRepository.findById(rs.getInt("movie_id")).orElse(null);
				LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
				reviews.add(new Review(id, rating, description, movie, createdAt));
			}
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return reviews;
	};

	private final Function<ResultSet, Review> reviewResultSetExtractor = (ResultSet rs) -> {
		Review review = null;
		try {
			if (rs.next()){
				int id = rs.getInt("id");
				int rating = rs.getInt("rating");
				String description = rs.getString("description");
				Movie movie = movieRepository.findById(rs.getInt("movie_id")).orElse(null);
				LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
				review = new Review(id, rating, description, movie, createdAt);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return review;
	};

	public ReviewRepositoryImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, rating, description, msa_user_id, movie_id, created_at " +
			"FROM review WHERE id = ?";

	@Override
	public Optional<Review> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)
		) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(reviewResultSetExtractor.apply(resultSet));
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_USER_ID = "SELECT id, rating, description, msa_user_id, " +
			"movie_id, created_at " +
			"FROM review WHERE msa_user_id = ?";

	@Override
	public List<Review> findByUserId(Integer userId) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)
		) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			return reviewListResultSetExtractor.apply(resultSet);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return new ArrayList<>();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_MOVIE_ID = "SELECT id, rating, description, " +
			"msa_user_id, movie_id, created_at " +
			"FROM review WHERE movie_id = ?";

	@Override
	public List<Review> findByMovieId(Integer movieId) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MOVIE_ID)
		) {
			statement.setInt(1, movieId);
			ResultSet resultSet = statement.executeQuery();
			return reviewListResultSetExtractor.apply(resultSet);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return new ArrayList<>();
		}
	}

	//language=SQL
	private final static String SQL_SAVE = "INSERT INTO review(rating, description, msa_user_id, movie_id, created_at) " +
			"VALUES (?, ?, ?, ?, ?)";

	@Override
	public Review save(Review review, int userId) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_SAVE)
		) {
			statement.setInt(1, review.getRating());
			statement.setString(2, review.getDescription());
			statement.setInt(3, userId);
			statement.setInt(4, review.getMovie().getId());
			statement.setTimestamp(5, Timestamp.valueOf(review.getCreatedAt()));
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()){
					review.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Generated key extraction error");
				}
			}
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return review;
	}
}
