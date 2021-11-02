package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewModalDTO;

import java.util.Optional;

public interface ReviewService {

	Optional<ReviewDTO> findById(Integer id);
	Optional<ReviewModalDTO> getReviewModalDTO(Integer id);
	void create(ReviewForm reviewForm);
}
