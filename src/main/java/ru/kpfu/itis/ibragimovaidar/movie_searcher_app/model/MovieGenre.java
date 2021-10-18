package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.util.Objects;
import java.util.StringJoiner;

public class MovieGenre {

	private Integer id;
	private String name;

	public MovieGenre() {
	}

	public MovieGenre(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MovieGenre that = (MovieGenre) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", MovieGenre.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("name='" + name + "'")
				.toString();
	}
}
