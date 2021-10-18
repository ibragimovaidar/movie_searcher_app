package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;

import java.util.Optional;

public interface MovieRepository {

	Optional<Movie> findById(Integer id);
	Movie save(Movie movie);
}
