package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	private final MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Optional<MovieDTO> findById(Integer id) {
		return movieRepository.findById(id).map(MovieDTO::from);
	}

	@Override
	public List<MovieDTO> findByMovieGenre(MovieGenre movieGenre) {
		return movieRepository.findByMovieGenre(movieGenre).stream()
				.map(MovieDTO::from)
				.collect(Collectors.toList());
	}

	@Override
	public List<MovieDTO> findByMovieGenreId(Integer movieGenreId) {
		return findByMovieGenre(new MovieGenre(movieGenreId, null));
	}
}
