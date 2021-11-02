package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Review;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class ReviewDTO {

	private final Integer id;
	private final Integer rating;
	private final String description;
	private final Movie movie;
	private final LocalDateTime createdAt;

	public ReviewDTO(Integer id, Integer rating, String description, Movie movie, LocalDateTime createdAt) {
		this.id = id;
		this.rating = rating;
		this.description = description;
		this.movie = movie;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
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
				.add("id='" + id + "'")
				.add("rating='" + rating + "'")
				.add("description='" + description + "'")
				.add("movie=" + movie)
				.add("createdAt=" + createdAt)
				.toString();
	}

	public static ReviewDTO from(Review review){
		return new ReviewDTO(
				review.getId(),
				review.getRating(),
				review.getDescription(),
				review.getMovie(),
				review.getCreatedAt()
		);
	}
}
