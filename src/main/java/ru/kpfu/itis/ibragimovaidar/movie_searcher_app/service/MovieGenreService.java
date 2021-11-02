package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieGenreDTO;

import java.util.List;
import java.util.Optional;

public interface MovieGenreService {

	List<MovieGenreDTO> findAll();
	Optional<MovieGenreDTO> findById(Integer id);
}
