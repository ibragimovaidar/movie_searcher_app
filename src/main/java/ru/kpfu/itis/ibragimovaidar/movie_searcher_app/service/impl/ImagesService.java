package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.PropertiesUtil;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ImageRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ImageResizeService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.UUID;

public class ImagesService {

	private final ImageResizeService imageResizeService;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ImagesService.class);

	private static final String IMAGES_PATH_PROPERTY_NAME = "images.path";
	private static final String IMAGES_PATH = PropertiesUtil.getProperty(IMAGES_PATH_PROPERTY_NAME);

	public ImagesService(ImageResizeService imageResizeService, UserRepository userRepository, ImageRepository imageRepository) {
		this.imageResizeService = imageResizeService;
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	public Optional<ImageMetadata> findById(Integer imageMetadataId){
		return imageRepository.findById(imageMetadataId);
	}

	public InputStream getImage(Integer imageMetadataId, int height, int weight) throws ServiceException {
		ImageMetadata imageMetadata = imageRepository.findById(imageMetadataId).orElseThrow(ServiceException::new);
		try {
			return Files.newInputStream(Paths.get(imageMetadata.getFilename()), StandardOpenOption.READ);
		} catch (IOException e) {
			LOGGER.error("Image opening error", e);
			throw new ServiceException(e);
		}
	}

	public InputStream getResizedImage(String imageUUID, int height, int width) throws ServiceException {
		ImageMetadata imageMetadata = imageRepository.findByUUID(imageUUID).orElseThrow(ServiceException::new);
		Path originalImagePath = Paths.get(IMAGES_PATH, imageMetadata.getFolder(), imageMetadata.getFilename());
		Path resizedImagePath = formatPath(originalImagePath, height, width);
		try {
			if (Files.exists(resizedImagePath)){
				return Files.newInputStream(resizedImagePath, StandardOpenOption.READ);
			}
			Files.createDirectories(resizedImagePath.getParent());
			imageResizeService.resizeImage(originalImagePath, resizedImagePath, height, width);
			return Files.newInputStream(resizedImagePath, StandardOpenOption.READ);
		} catch(IOException e){
			LOGGER.error("", e);
			throw new ServiceException(e);
		}
	}

	public ImageMetadata saveImage(Integer userId, Part imageFilePart) throws ServiceException {
		String filename = imageFilePart.getSubmittedFileName();
		String folder = UUID.randomUUID().toString();
		ImageMetadata imageMetadata = new ImageMetadata(filename, folder);

		Path path = Paths.get(IMAGES_PATH, folder, filename);
		try {
			Files.createDirectories(path.getParent());
			imageFilePart.write(path.toString());
			imageRepository.save(imageMetadata);
			User user = userRepository.findById(userId).orElseThrow(ServiceException::new);
			user.setImageMetadata(imageMetadata);
			userRepository.update(user);
		} catch (IOException e) {
			LOGGER.error("", e);
			throw new ServiceException(e);
		}
		return imageMetadata;
	}

	private Path formatPath(Path filePath, int targetHeight, int targetWidth){
		String filePathString = filePath.toString();
		String fileExtension = filePathString.substring(filePathString.lastIndexOf('.'));
		String resultFilePathString = filePathString.substring(0, filePathString.lastIndexOf(".")) +
				targetHeight + 'x' + targetWidth + fileExtension;
		return Paths.get(resultFilePathString);
	}
}
