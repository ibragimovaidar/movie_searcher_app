package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.UserRole;

import java.util.StringJoiner;

public class UserDTO {

	private final Integer id;
	private final String username;
	private final UserRole role;

	public UserDTO(Integer id, String username, UserRole role) {
		this.id = id;
		this.username = username;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public UserRole getRole() {
		return role;
	}

	public static UserDTO from(User user){
		return new UserDTO(
				user.getId(),
				user.getUsername(),
				user.getRole());
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("username='" + username + "'")
				.add("role='" + role + "'")
				.toString();
	}
}
