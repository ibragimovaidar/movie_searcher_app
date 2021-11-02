package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ImageRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieCommentaryRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieGenreRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.PersonRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.ReviewRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.ImageRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.MovieCommentaryRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.MovieGenreRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.MovieRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.PersonRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.ReviewRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.UserRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.*;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext context = contextEvent.getServletContext();

		ImageRepository imageRepository = new ImageRepositoryImpl();
		PersonRepository personRepository = new PersonRepositoryImpl(imageRepository);
		MovieGenreRepository movieGenreRepository = new MovieGenreRepositoryImpl();
		MovieCommentaryRepository movieCommentaryRepository = new MovieCommentaryRepositoryImpl();
		MovieRepository movieRepository =
				new MovieRepositoryImpl(imageRepository, personRepository, movieGenreRepository, movieCommentaryRepository);
		ReviewRepository reviewRepository = new ReviewRepositoryImpl();
		UserRepository userRepository = new UserRepositoryImpl(imageRepository, reviewRepository);

		movieCommentaryRepository.setUserRepository(userRepository);

		MovieService movieService = new MovieServiceImpl(movieRepository);
		context.setAttribute(MovieService.class.getName(), movieService);

		UserService userService = new UserServiceImpl(userRepository);
		context.setAttribute(UserService.class.getName(), userService);

		PersonService personService = new PersonServiceImpl(personRepository);
		context.setAttribute(PersonService.class.getName(), personService);

		ImageResizeService imageResizeService = new ImageResizeServiceImpl();
		context.setAttribute(ImageResizeService.class.getName(), imageResizeService);

		ImagesService imagesService = new ImagesService(imageResizeService, userRepository, imageRepository);
		context.setAttribute(ImagesService.class.getName(), imagesService);

		ReviewService reviewService = new ReviewServiceImpl(movieRepository, reviewRepository);
		context.setAttribute(ReviewService.class.getName(), reviewService);

		MovieGenreService movieGenreService = new MovieGenreServiceImpl(movieGenreRepository);
		context.setAttribute(MovieGenreService.class.getName(), movieGenreService);

		MovieCommentaryService movieCommentaryService =
				new MovieCommentaryServiceImpl(movieCommentaryRepository, userRepository);
		context.setAttribute(MovieCommentaryService.class.getName(), movieCommentaryService);

		ObjectMapper objectMapper = new ObjectMapper();
		context.setAttribute(ObjectMapper.class.getName(), objectMapper);
	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		ServletContextListener.super.contextDestroyed(contextEvent);
	}
}
