package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;

import java.util.Optional;

public interface UserRepository {

	Optional<User> findById(Integer id);
	Optional<User> findByUsername(String username);
	User save(User user);
}
