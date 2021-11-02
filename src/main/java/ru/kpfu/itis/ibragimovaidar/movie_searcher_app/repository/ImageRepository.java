package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;

import java.util.Optional;

public interface ImageRepository {

	Optional<ImageMetadata> findById(Integer id);
	Optional<ImageMetadata> findByUUID(String uuid);
	ImageMetadata save(ImageMetadata imageMetadata);
}
