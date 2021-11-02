package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Movie;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MovieDTO {

	private final Integer id;
	private final String name;
	private final LocalDate dateOfRelease;
	private final String country;
	private final Integer averageRating;
	private final String description;
	private final String movieGenreName;
	private final List<Person> participants;
	private final ImageMetadata imageMetadata;
	private final List<MovieCommentaryDTO> movieCommentaryDTOList;

	public MovieDTO(Integer id, String name, LocalDate dateOfRelease, String country, Integer averageRating, String description, String movieGenreName, List<Person> participants, ImageMetadata imageMetadata, List<MovieCommentaryDTO> movieCommentaryDTOList) {
		this.id = id;
		this.name = name;
		this.dateOfRelease = dateOfRelease;
		this.country = country;
		this.averageRating = averageRating;
		this.description = description;
		this.movieGenreName = movieGenreName;
		this.participants = participants;
		this.imageMetadata = imageMetadata;
		this.movieCommentaryDTOList = movieCommentaryDTOList;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfRelease() {
		return dateOfRelease;
	}

	public String getCountry() {
		return country;
	}

	public Integer getAverageRating() {
		return averageRating;
	}

	public String getDescription() {
		return description;
	}

	public String getMovieGenreName() {
		return movieGenreName;
	}

	public List<Person> getParticipants() {
		return participants;
	}

	public ImageMetadata getImageMetadata() {
		return imageMetadata;
	}

	public List<MovieCommentaryDTO> getMovieCommentaryDTOList() {
		return movieCommentaryDTOList;
	}

	public static MovieDTO from(Movie movie){
		return new MovieDTO(
				movie.getId(),
				movie.getName(),
				movie.getDateOfRelease(),
				movie.getCountry(),
				movie.getAverageRating(),
				movie.getDescription(),
				movie.getMovieGenre().getName(),
				movie.getParticipants(),
				movie.getImageMetadata(),
				movie.getMovieCommentaries().stream()
						.map(MovieCommentaryDTO::from)
						.collect(Collectors.toList())
		);
	}
}
