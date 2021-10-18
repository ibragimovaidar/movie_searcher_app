package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.PasswordUtil;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserSignInForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserSignUpForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.User;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<UserDTO> findById(Integer id){
		Optional<User> user = userRepository.findById(id);
		return user.map(UserDTO::from);
	}

	public UserDTO createUser(UserSignUpForm userSignUpForm){
		User user = new User();
		user.setUsername(userSignUpForm.getUsername());
		user.setPasswordHash(PasswordUtil.encrypt(userSignUpForm.getPassword()));
		user.setEmail(userSignUpForm.getEmail());
		user.setFirstName(userSignUpForm.getFirstName());
		user.setLastName(userSignUpForm.getLastName());
		user.setDateOfBirth(userSignUpForm.getDateOfBirth());
		return UserDTO.from(userRepository.save(user));
	}

	public Optional<UserDTO> authenticateUser(UserSignInForm userSignInForm){
		Optional<User> user = userRepository.findByUsername(userSignInForm.getUsername());
		if (user.isPresent() && user.get().getPasswordHash()
				.equals(PasswordUtil.encrypt(userSignInForm.getPassword()))){
			return user.map(UserDTO::from);
		}
		return Optional.empty();
	}
}
