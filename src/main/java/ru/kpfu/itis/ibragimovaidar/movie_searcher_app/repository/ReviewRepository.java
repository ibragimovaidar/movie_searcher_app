package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

	Optional<Review> findById(Integer id);
	List<Review> findByUserId(Integer userId);
	List<Review> findByMovieId(Integer movieId);
	Review save(Review review, int userId);
}
