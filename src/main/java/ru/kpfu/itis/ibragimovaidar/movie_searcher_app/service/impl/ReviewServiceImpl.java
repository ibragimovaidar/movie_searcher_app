package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewModalDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Review;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ReviewRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ReviewService;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

	private final MovieRepository movieRepository;
	private final ReviewRepository reviewRepository;

	public ReviewServiceImpl(MovieRepository movieRepository, ReviewRepository reviewRepository) {
		this.movieRepository = movieRepository;
		this.reviewRepository = reviewRepository;
	}

	@Override
	public Optional<ReviewDTO> findById(Integer id) {
		return reviewRepository.findById(id).map(ReviewDTO::from);
	}

	@Override
	public Optional<ReviewModalDTO> getReviewModalDTO(Integer id) {
		return findById(id).map(ReviewModalDTO::from);
	}

	@Override
	public void create(ReviewForm reviewForm) {
		Movie movie = movieRepository.findById(reviewForm.getMovieId()).orElse(null);
		Review review = new Review(
				reviewForm.getRating(),
				reviewForm.getDescription(),
				movie,
				LocalDateTime.now()
		);
		reviewRepository.save(review, reviewForm.getUserId());
	}
}
