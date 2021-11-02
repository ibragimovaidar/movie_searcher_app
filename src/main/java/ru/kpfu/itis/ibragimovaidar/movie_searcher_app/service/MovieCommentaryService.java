package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieCommentaryForm;

public interface MovieCommentaryService {

	void createCommentary(MovieCommentaryForm movieCommentaryForm);
	void upVote(Integer movieCommentaryId);
	void downVote(Integer movieCommentaryId);
}
