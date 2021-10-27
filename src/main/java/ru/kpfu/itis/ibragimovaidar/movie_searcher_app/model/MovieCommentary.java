package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class MovieCommentary {

	private Integer id;
	private String text;
	private LocalDateTime createdAt;
	private Integer rating;
	private User owner;

	public MovieCommentary() {
	}

	public MovieCommentary(String text, LocalDateTime createdAt, Integer rating, User owner) {
		this.text = text;
		this.createdAt = createdAt;
		this.rating = rating;
		this.owner = owner;
	}

	public MovieCommentary(Integer id, String text, LocalDateTime createdAt, Integer rating, User owner) {
		this.id = id;
		this.text = text;
		this.createdAt = createdAt;
		this.rating = rating;
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MovieCommentary that = (MovieCommentary) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", MovieCommentary.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("text='" + text + "'")
				.add("owner=" + owner)
				.toString();
	}
}
