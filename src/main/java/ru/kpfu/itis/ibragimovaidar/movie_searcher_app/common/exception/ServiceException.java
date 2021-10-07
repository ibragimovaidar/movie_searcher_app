package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception;

public class ServiceException extends Exception {

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
