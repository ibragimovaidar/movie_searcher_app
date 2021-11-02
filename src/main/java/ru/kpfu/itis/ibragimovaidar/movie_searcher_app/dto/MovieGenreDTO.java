package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;

public class MovieGenreDTO {

	private final Integer id;
	private final String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MovieGenreDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static MovieGenreDTO from(MovieGenre movieGenre){
		return new MovieGenreDTO(
				movieGenre.getId(),
				movieGenre.getName()
		);
	}
}
