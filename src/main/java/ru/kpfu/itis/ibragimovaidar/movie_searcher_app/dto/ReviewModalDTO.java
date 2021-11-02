package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

public class ReviewModalDTO {

	private final Integer id;
	private final Integer rating;
	private final String description;
	private final Integer movieId;
	private final String movieName;

	public ReviewModalDTO(Integer id, Integer rating, String description, Integer movieId, String movieName) {
		this.id = id;
		this.rating = rating;
		this.description = description;
		this.movieId = movieId;
		this.movieName = movieName;
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

	public Integer getMovieId() {
		return movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public static ReviewModalDTO from(ReviewDTO reviewDTO){
		return new ReviewModalDTO(
				reviewDTO.getId(),
				reviewDTO.getRating(),
				reviewDTO.getDescription(),
				reviewDTO.getMovie().getId(),
				reviewDTO.getMovie().getName()
		);
	}
}
