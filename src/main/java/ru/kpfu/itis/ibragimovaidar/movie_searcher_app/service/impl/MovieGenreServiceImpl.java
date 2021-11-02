package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieGenreDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieGenreRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieGenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieGenreServiceImpl implements MovieGenreService {

	private final MovieGenreRepository movieGenreRepository;

	public MovieGenreServiceImpl(MovieGenreRepository movieGenreRepository) {
		this.movieGenreRepository = movieGenreRepository;
	}

	@Override
	public List<MovieGenreDTO> findAll() {
		return movieGenreRepository.findAll().stream()
				.map(MovieGenreDTO::from)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<MovieGenreDTO> findById(Integer id) {
		return movieGenreRepository.findById(id).map(MovieGenreDTO::from);
	}
}
