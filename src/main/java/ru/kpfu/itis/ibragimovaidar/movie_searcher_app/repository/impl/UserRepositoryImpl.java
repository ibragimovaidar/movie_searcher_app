package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class UserRepositoryImpl implements UserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

	private static final Function<ResultSet, User> userResultSetExtractor = rs -> {
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
				user = new User(id, username, passwordHash, email, firstName, lastName, middleName, dateOfBirth, description, new ArrayList<>());
			}
		} catch (SQLException e) {
			LOGGER.error(" " ,e);
		}
		return user;
	};

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT u.id, u.username, u.password_hash, u.email, u.first_name, u.last_name," +
			" u.middle_name, u.date_of_birth, u.description " +
			"FROM msa_user u WHERE u.id = ?";

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
	private static final String SQL_FIND_BY_USERNAME = "SELECT u.id, u.username, u.password_hash, u.email, u.first_name, u.last_name," +
			" u.middle_name, u.date_of_birth, u.description " +
			"FROM msa_user u WHERE u.username = ?";

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
	private static final String SQL_SAVE = "INSERT INTO msa_user " + "(username, password_hash, email, first_name, last_name, middle_name, date_of_birth, description) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
