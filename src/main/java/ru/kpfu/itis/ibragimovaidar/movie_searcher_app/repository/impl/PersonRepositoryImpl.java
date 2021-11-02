package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Person;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.PersonType;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ImageRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.PersonRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PersonRepositoryImpl implements PersonRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryImpl.class);

	private ImageRepository imageRepository;

	public PersonRepositoryImpl(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	private final Function<ResultSet, List<Person>> personListResultSetExtractor = (ResultSet rs) -> {
		List<Person> persons = new ArrayList<>();
		try {
			while (rs.next()){
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String middleName = rs.getString("middle_name");
				String description = rs.getString("description");
				PersonType personType = PersonType.valueOf(rs.getString("person_type"));
				ImageMetadata imageMetadata = imageRepository.findById(rs.getInt("image_metadata_id")).orElse(null);
				Person person = new Person(id, firstName, lastName, middleName, description, personType, imageMetadata);
				persons.add(person);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			LOGGER.error("personResultSetExtractor error", throwables);
		}
		return persons;
	};

	private final Function<ResultSet, Person> personResultSetExtractor = (ResultSet rs) -> {
		Person person = null;
		try {
			if (rs.next()){
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String middleName = rs.getString("middle_name");
				String description = rs.getString("description");
				PersonType personType = PersonType.valueOf(rs.getString("person_type"));
				ImageMetadata imageMetadata = imageRepository.findById(rs.getInt("image_metadata_id")).orElse(null);
				person = new Person(id, firstName, lastName, middleName, description, personType, imageMetadata);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return person;
	};

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, first_name, last_name, middle_name, " +
			"description, person_type, image_metadata_id " +
			"FROM person WHERE id = ?";

	@Override
	public Optional<Person> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)
		){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(personResultSetExtractor.apply(resultSet));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_MOVIE_ID = "SELECT " +
			"p.id, p.first_name, p.last_name, p.middle_name, p.description, p.person_type, p.image_metadata_id " +
			"FROM person p " +
			"JOIN movies_participators mp ON p.id = mp.person_id " +
			"JOIN movie m on mp.movie_id = m.id WHERE m.id = ?";

	@Override
	public List<Person> findByMovieId(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_MOVIE_ID)
		) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			return personListResultSetExtractor.apply(resultSet);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
		}
		return new ArrayList<>();
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO person(first_name, last_name, middle_name, description, " +
			"person_type, image_metadata_id) " +
			"VALUES (?, ?, ?, ?, ?::person_type, ?)";
	@Override
	public Person save(Person person) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
		) {
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setString(3, person.getMiddleName());
			statement.setString(4, person.getDescription());
			statement.setString(5, person.getPersonType().toString());
			statement.setInt(6, person.getImage().getId());
			int affectedRows = statement.executeUpdate();
			if (affectedRows < 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()){
					person.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Generated key extraction error");
				}
			}
		} catch (SQLException throwables) {
			LOGGER.error("Save error", throwables);
			throwables.printStackTrace();
		}
		return person;
	}

	@Override
	public Person update(Person person) {
		return null;
	}
}
