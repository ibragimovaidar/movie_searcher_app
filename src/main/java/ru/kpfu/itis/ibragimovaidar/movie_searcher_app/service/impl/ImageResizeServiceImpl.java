package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ImageResizeService;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class ImageResizeServiceImpl implements ImageResizeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageResizeServiceImpl.class);

	public void resizeImage(Path originalImageFilepath, Path targetImageFilePath, int targetHeight, int targetWidth) throws ServiceException {

		try (InputStream inputStream = Files.newInputStream(originalImageFilepath, READ)){
			BufferedImage originalImage = ImageIO.read(inputStream);

			BufferedImage resizedImage =
					Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);

			File file = targetImageFilePath.toFile();
			ImageIO.write(resizedImage, "JPEG", file);
		} catch (IOException e) {
			LOGGER.error("Resize image error", e);
			throw new ServiceException(e);
		}
	}

	private static BufferedImage convertToBufferedImage(Image img) {

		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bi = new BufferedImage(
				img.getWidth(null), img.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics2D = bi.createGraphics();
		graphics2D.drawImage(img, 0, 0, null);
		graphics2D.dispose();

		return bi;
	}

}
