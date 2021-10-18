package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class Review {

	private Integer id;
	private Integer rating;
	private String description;
	private Movie movie;
	private LocalDateTime createdAt;

	public Review() {
	}

	public Review(Integer rating, String description, Movie movie, LocalDateTime createdAt) {
		this.rating = rating;
		this.description = description;
		this.movie = movie;
		this.createdAt = createdAt;
	}

	public Review(Integer id, Integer rating, String description, Movie movie, LocalDateTime createdAt) {
		this.id = id;
		this.rating = rating;
		this.description = description;
		this.movie = movie;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Review review = (Review) o;
		return id.equals(review.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Review.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("rating=" + rating)
				.toString();
	}
}
