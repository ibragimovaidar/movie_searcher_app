package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Review;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ReviewDTO {

	private final Integer rating;
	private final String description;
	private final Movie movie;
	private final LocalDateTime createdAt;

	public ReviewDTO(Integer rating, String description, Movie movie, LocalDateTime createdAt) {
		this.rating = rating;
		this.description = description;
		this.movie = movie;
		this.createdAt = createdAt;
	}

	public Integer getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}

	public Movie getMovie() {
		return movie;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ReviewDTO.class.getSimpleName() + "[", "]")
				.add("rating=" + rating)
				.add("description='" + description + "'")
				.add("movie=" + movie)
				.add("createdAt=" + createdAt)
				.toString();
	}

	public static ReviewDTO from(Review review){
		return new ReviewDTO(
				review.getRating(),
				review.getDescription(),
				review.getMovie(),
				review.getCreatedAt()
		);
	}
}
