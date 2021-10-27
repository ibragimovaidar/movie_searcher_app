package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.MovieGenre;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.*;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.*;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ImageResizeService;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieService;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.PersonService;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl.*;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.UserService;

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
		MovieRepository movieRepository =
				new MovieRepositoryImpl(imageRepository, personRepository, movieGenreRepository);
		ReviewRepository reviewRepository = new ReviewRepositoryImpl(movieRepository);
		UserRepository userRepository = new UserRepositoryImpl(imageRepository, reviewRepository);

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

	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		ServletContextListener.super.contextDestroyed(contextEvent);
	}
}
