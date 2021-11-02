package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieCommentary;

import java.util.List;
import java.util.Optional;

public interface MovieCommentaryRepository {

	Optional<MovieCommentary> findById(Integer id);
	List<MovieCommentary> findByMovieId(Integer movieId);
	MovieCommentary save(MovieCommentary movieCommentary, Integer movieId);

	// to solve cycle dependency (
	void setUserRepository(UserRepository userRepository);
}
