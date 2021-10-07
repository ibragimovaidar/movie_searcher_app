package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserSignInForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserSignUpForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;

import java.util.Optional;

public interface UserService {

	Optional<UserDTO> findById(Integer id);
	UserDTO createUser(UserSignUpForm userSignUpForm);
	Optional<UserDTO> authenticateUser(UserSignInForm userSignInForm);
}
