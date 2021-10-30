package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

public class MovieCommentaryForm {

	private final String text;
	private final Integer ownerId;
	private final Integer movieId;

	public MovieCommentaryForm(String text, Integer ownerId, Integer movieId) {
		this.text = text;
		this.ownerId = ownerId;
		this.movieId = movieId;
	}

	public String getText() {
		return text;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public Integer getMovieId() {
		return movieId;
	}
}
