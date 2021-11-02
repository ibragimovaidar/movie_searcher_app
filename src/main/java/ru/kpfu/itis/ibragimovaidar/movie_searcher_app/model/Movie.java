package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Movie {

	private Integer id;
	private String name;
	private LocalDate dateOfRelease;
	private String country;
	private Integer averageRating;
	private MovieGenre movieGenre;
	private List<Person> participants;
	private String description;
	private ImageMetadata imageMetadata;
	private List<MovieCommentary> movieCommentaries;

	public Movie() {
	}

	public Movie(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Movie(String name, LocalDate dateOfRelease, String country, Integer averageRating, MovieGenre movieGenre, List<Person> participants, String description, ImageMetadata imageMetadata, List<MovieCommentary> movieCommentaries) {
		this.name = name;
		this.dateOfRelease = dateOfRelease;
		this.country = country;
		this.averageRating = averageRating;
		this.movieGenre = movieGenre;
		this.participants = participants;
		this.description = description;
		this.imageMetadata = imageMetadata;
		this.movieCommentaries = movieCommentaries;
	}

	public Movie(Integer id, String name, LocalDate dateOfRelease, String country, Integer averageRating, MovieGenre movieGenre, List<Person> participants, String description, ImageMetadata imageMetadata, List<MovieCommentary> movieCommentaries) {
		this.id = id;
		this.name = name;
		this.dateOfRelease = dateOfRelease;
		this.country = country;
		this.averageRating = averageRating;
		this.movieGenre = movieGenre;
		this.participants = participants;
		this.description = description;
		this.imageMetadata = imageMetadata;
		this.movieCommentaries = movieCommentaries;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(LocalDate dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Integer averageRating) {
		this.averageRating = averageRating;
	}

	public MovieGenre getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(MovieGenre movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Person> participants) {
		this.participants = participants;
	}

	public ImageMetadata getImageMetadata() {
		return imageMetadata;
	}

	public void setImageMetadata(ImageMetadata imageMetadata) {
		this.imageMetadata = imageMetadata;
	}

	public List<MovieCommentary> getMovieCommentaries() {
		return movieCommentaries;
	}

	public void setMovieCommentaries(List<MovieCommentary> movieCommentaries) {
		this.movieCommentaries = movieCommentaries;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Movie movie = (Movie) o;
		return id.equals(movie.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Movie.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("name='" + name + "'")
				.toString();
	}
}
