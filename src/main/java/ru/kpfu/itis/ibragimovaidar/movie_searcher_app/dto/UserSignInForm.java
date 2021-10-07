package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;

import java.util.StringJoiner;

public class UserSignInForm {

	private String username;
	private String password;

	public UserSignInForm() {
	}

	public UserSignInForm(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UserSignInForm.class.getSimpleName() + "[", "]")
				.add("username='" + username + "'")
				.add("password='" + password + "'")
				.toString();
	}
}
