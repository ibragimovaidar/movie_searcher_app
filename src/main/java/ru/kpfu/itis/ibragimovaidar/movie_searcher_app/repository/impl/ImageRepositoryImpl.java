package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.ConnectionManager;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ImageRepository;

import java.sql.*;
import java.util.Optional;
import java.util.function.Function;

public class ImageRepositoryImpl implements ImageRepository {

	private final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryImpl.class);

	private final static Function<ResultSet, ImageMetadata> imageMetadataResultSetExtractor = rs -> {
		ImageMetadata imageMetadata = null;
		try {
			if (rs.next()){
				int id = rs.getInt("id");
				String filename = rs.getString("filename");
				String folder = rs.getString("folder");
				imageMetadata = new ImageMetadata(id, filename, folder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageMetadata;
	};

	//language=SQL
	private static final String SQL_FIND_BY_ID = "SELECT id, filename, folder FROM image_metadata " +
			"WHERE id = ?";

	@Override
	public Optional<ImageMetadata> findById(Integer id) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)
		) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			ImageMetadata imageMetadata = imageMetadataResultSetExtractor.apply(resultSet);
			return Optional.ofNullable(imageMetadata);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_FIND_BY_UUID = "SELECT id, filename, folder FROM image_metadata " +
			"WHERE folder = ?";

	@Override
	public Optional<ImageMetadata> findByUUID(String uuid) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_UUID)
		) {
			preparedStatement.setString(1, uuid);
			ResultSet resultSet = preparedStatement.executeQuery();
			ImageMetadata imageMetadata = imageMetadataResultSetExtractor.apply(resultSet);
			return Optional.ofNullable(imageMetadata);
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
			return Optional.empty();
		}
	}

	//language=SQL
	private static final String SQL_SAVE = "INSERT INTO image_metadata(filename, folder) " +
			"VALUES (?, ?)";

	@Override
	public ImageMetadata save(ImageMetadata imageMetadata) {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS)
		){
			statement.setString(1, imageMetadata.getFilename());
			statement.setString(2, imageMetadata.getFolder());
			int affectedRows = statement.executeUpdate();
			if (affectedRows != 1){
				throw new SQLException("Save error");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()){
				if (generatedKeys.next()){
					imageMetadata.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Generated key extraction error");
				}
			}
		} catch (SQLException throwables) {
			LOGGER.error("", throwables);
		}
		return imageMetadata;
	}
}
