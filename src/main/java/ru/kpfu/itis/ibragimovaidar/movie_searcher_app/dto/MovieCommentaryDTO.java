package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieCommentary;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class MovieCommentaryDTO {

	private final Integer id;
	private final String text;
	private final LocalDateTime createdAt;
	private final Integer rating;
	private final String ownerUsername;


	public MovieCommentaryDTO(Integer id, String text, LocalDateTime createdAt, Integer rating, String ownerUsername) {
		this.id = id;
		this.text = text;
		this.createdAt = createdAt;
		this.rating = rating;
		this.ownerUsername = ownerUsername;
	}

	public Integer getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Integer getRating() {
		return rating;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", MovieCommentaryDTO.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("createdAt=" + createdAt)
				.add("ownerUsername='" + ownerUsername + "'")
				.toString();
	}

	public static MovieCommentaryDTO from(MovieCommentary movieCommentary){
		return new MovieCommentaryDTO(
				movieCommentary.getId(),
				movieCommentary.getText(),
				movieCommentary.getCreatedAt(),
				movieCommentary.getRating(),
				movieCommentary.getOwner().getUsername()
		);
	}
}
