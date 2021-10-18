package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieDTO;

import java.util.Optional;

public interface MovieService {

	Optional<MovieDTO> findById(Integer id);
}
