package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;

import java.util.StringJoiner;

public class UserDTO {

	private final Integer id;
	private final String username;

	public UserDTO(Integer id, String username) {
		this.id = id;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public static UserDTO from(User user){
		return new UserDTO(
				user.getId(),
				user.getUsername()
		);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("username='" + username + "'")
				.toString();
	}
}
