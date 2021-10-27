package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

	Optional<Movie> findById(Integer id);
	Movie save(Movie movie);
	List<Movie> findByMovieGenre(MovieGenre movieGenre);
	List<Movie> findByMovieGenreName(String name);
}
