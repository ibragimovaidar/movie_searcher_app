package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Review;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.UserRole;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ImageRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ReviewRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserRepositoryImpl implements UserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

	private ImageRepository imageRepository;
	private ReviewRepository reviewRepository;

	private final Function<ResultSet, User> userResultSetExtractor = rs -> {
		User user = null;
		try {
			if (rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String passwordHash = rs.getString("password_hash");
				String email = rs.getString("email");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String middleName = rs.getString("middle_name");
				LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
				String description = rs.getString("description");
				UserRole role = UserRole.valueOf(rs.getString("msa_role"));
				ImageMetadata imageMetadata = imageRepository.findById(rs.getInt("image_metadata_id")).orElse(null);
				List<Review> reviews = reviewRepository.findByUserId(id);
				user = new User(id, username, passwordHash, email, firstName, lastName, middleName,
						dateOfBirth, description, reviews, role, imageMetadata);
			}
		} catch (SQLException e) {
			LOGGER.error(" " ,e);
		}
		return user;
	};

	public UserRepositoryImpl(ImageRepository imageRepository, ReviewRepository reviewRepository) {
		this.imageRepository = imageRepository;
		this.reviewRepository = reviewRepository;
	}

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, username, password_hash, email, first_name, last_name, " +
			"middle_name, date_of_birth, description, msa_role, image_metadata_id " +
			"FROM msa_user WHERE id = ?";



	@Override
	public Optional<User> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
		){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			User user = userResultSetExtractor.apply(resultSet);
			return Optional.ofNullable(user);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return Optional.empty();
	}

	//language=SQL
	private static final String SQL_FIND_BY_USERNAME = "SELECT id, username, password_hash, email, " +
			"first_name, last_name, middle_name, date_of_birth, description, msa_role, image_metadata_id " +
			"FROM msa_user WHERE username = ?";

	@Override
	public Optional<User> findByUsername(String username) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USERNAME);
		){
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			User user = userResultSetExtractor.apply(resultSet);
			return Optional.ofNullable(user);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			throwables.printStackTrace();
		}
		return Optional.empty();
	}

	//language=SQL
	private static final String SQL_UPDATE = "UPDATE msa_user SET username = ?, password_hash = ?, email = ?, first_name = ?, last_name = ?, middle_name = ?, " +
			"date_of_birth = ?, description = ?, msa_role = ?::msa_role, image_metadata_id = ? WHERE id = ?";

	@Override
	public User update(User user) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
		){
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPasswordHash());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getMiddleName());
			statement.setDate(7, Date.valueOf(user.getDateOfBirth()));
			statement.setString(8, user.getDescription());
			statement.setString(9, user.getRole().toString());
			if (user.getImageMetadata() != null){
				statement.setInt(10, user.getImageMetadata().getId());
			} else {
				statement.setInt(10, 0);
			}
			statement.setInt(11, user.getId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 1){
				throw new SQLException("Update error");
			}
		} catch (SQLException throwables) {
			LOGGER.error("Update error", throwables);
			throwables.printStackTrace();
		}
		return user;
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO msa_user (username, password_hash, email, first_name, last_name, middle_name, date_of_birth, description, msa_role, image_metadata_id) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::msa_role, ?)";

	@Override
	public User save(User user) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
		){
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPasswordHash());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getMiddleName());
			statement.setDate(7, Date.valueOf(user.getDateOfBirth()));
			statement.setString(8, user.getDescription());
			statement.setString(9, user.getRole().toString());
			if (user.getImageMetadata() != null){
				statement.setInt(10, user.getImageMetadata().getId());
			} else {
				statement.setInt(10, -1);
			}
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()){
					user.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Generated key extraction error");
				}
			}
		} catch (SQLException throwables) {
			LOGGER.error("Save error", throwables);
			throwables.printStackTrace();
		}
		return user;
	}
}
