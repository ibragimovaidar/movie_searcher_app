package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception;

public class WrongUserOrPasswordException extends RuntimeException {

	public WrongUserOrPasswordException() {
	}

	public WrongUserOrPasswordException(String message) {
		super(message);
	}

	public WrongUserOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongUserOrPasswordException(Throwable cause) {
		super(cause);
	}
}
