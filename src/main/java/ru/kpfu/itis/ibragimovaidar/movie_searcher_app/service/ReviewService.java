package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewDTO;

import java.util.Optional;

public interface ReviewService {

	Optional<ReviewDTO> findById(Integer id);
	void create(ReviewDTO reviewDTO);
}
