package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import java.time.LocalDate;
import java.util.StringJoiner;

public class UserSignUpForm {

	private final String username;
	private final String password;
	private final String description;
	private final String email;
	private final String firstName;
	private final String lastName;
	private final LocalDate dateOfBirth;

	public UserSignUpForm(String username, String password, String description, String email, String firstName, String lastName, LocalDate dateOfBirth) {
		this.username = username;
		this.password = password;
		this.description = description;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserSignUpForm.class.getSimpleName() + "[", "]")
				.add("username='" + username + "'")
				.add("password='" + password + "'")
				.add("email='" + email + "'")
				.toString();
	}
}
