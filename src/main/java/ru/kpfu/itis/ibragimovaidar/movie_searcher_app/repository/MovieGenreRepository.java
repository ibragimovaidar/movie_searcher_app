package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;

import java.util.Optional;

public interface MovieGenreRepository {

	Optional<MovieGenre> findById(Integer id);
	MovieGenre save(MovieGenre movieGenre);
}
