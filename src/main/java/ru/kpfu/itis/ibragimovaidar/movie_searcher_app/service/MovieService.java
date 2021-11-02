package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;

import java.util.List;
import java.util.Optional;

public interface MovieService {

	Optional<MovieDTO> findById(Integer id);
	List<MovieDTO> findByMovieGenre(MovieGenre movieGenre);
	List<MovieDTO> findByMovieGenreId(Integer movieGenreId);
}
