package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieCommentaryForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieCommentary;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieCommentaryRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieCommentaryService;

import java.time.LocalDateTime;

public class MovieCommentaryServiceImpl implements MovieCommentaryService {

	private final MovieCommentaryRepository movieCommentaryRepository;
	private final UserRepository userRepository;

	public MovieCommentaryServiceImpl(MovieCommentaryRepository movieCommentaryRepository, UserRepository userRepository) {
		this.movieCommentaryRepository = movieCommentaryRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void createCommentary(MovieCommentaryForm movieCommentaryForm) {
		MovieCommentary movieCommentary = new MovieCommentary(
				movieCommentaryForm.getText(),
				LocalDateTime.now(),
				0,
				userRepository.findById(movieCommentaryForm.getOwnerId()).orElse(null)
		);
		movieCommentaryRepository.save(movieCommentary, movieCommentaryForm.getMovieId());
	}

	@Override
	public void upVote(Integer movieCommentaryId) {
		// TODO
		throw new RuntimeException("Not implemented");
	}

	@Override
	public void downVote(Integer movieCommentaryId) {
		// TODO
		throw new RuntimeException("Not implemented");
	}
}
