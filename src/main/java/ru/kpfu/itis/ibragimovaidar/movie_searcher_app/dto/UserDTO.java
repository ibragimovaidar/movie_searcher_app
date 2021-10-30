package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.UserRole;

import java.util.StringJoiner;

public class UserDTO {

	private final Integer id;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final UserRole role;
	private final String description;
	private final ImageMetadata imageMetadata;

	public UserDTO(Integer id, String username, String firstName, String lastName, UserRole role, String description, ImageMetadata imageMetadata) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.description = description;
		this.imageMetadata = imageMetadata;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDescription() {
		return description;
	}

	public ImageMetadata getImageMetadata() {
		return imageMetadata;
	}


	public static UserDTO from(User user){
		return new UserDTO(
				user.getId(),
				user.getUsername(),
				user.getFirstName(),
				user.getLastName(),
				user.getRole(),
				user.getDescription(),
				user.getImageMetadata()
		);
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
