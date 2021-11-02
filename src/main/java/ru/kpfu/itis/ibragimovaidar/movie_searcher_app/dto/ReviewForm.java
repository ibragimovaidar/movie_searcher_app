package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import java.util.StringJoiner;

public class ReviewForm {

	private final Integer userId;
	private final Integer movieId;
	private final Integer rating;
	private final String description;

	public ReviewForm(Integer userId, Integer movieId, Integer rating, String description) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public Integer getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ReviewForm.class.getSimpleName() + "[", "]")
				.add("userId=" + userId)
				.add("movieId=" + movieId)
				.add("rating=" + rating)
				.add("description='" + description + "'")
				.toString();
	}
}
