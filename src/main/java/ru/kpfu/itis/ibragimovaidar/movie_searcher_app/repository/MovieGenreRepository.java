package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;

import java.util.List;
import java.util.Optional;

public interface MovieGenreRepository {

	List<MovieGenre> findAll();
	Optional<MovieGenre> findById(Integer id);
	Optional<MovieGenre> findByName(String name);
	MovieGenre save(MovieGenre movieGenre);
}
