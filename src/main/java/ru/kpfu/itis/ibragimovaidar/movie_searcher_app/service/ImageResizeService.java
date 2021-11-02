package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception.ServiceException;

import java.nio.file.Path;

public interface ImageResizeService {

	void resizeImage(Path originalImageFilepath, Path targetImageFilePath, int targetHeight, int targetWidth) throws ServiceException;
}
