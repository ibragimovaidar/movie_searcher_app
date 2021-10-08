package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.impl.UserRepositoryImpl;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.UserService;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext context = contextEvent.getServletContext();

		UserRepository userRepository = new UserRepositoryImpl();
		UserService userService = new UserServiceImpl(userRepository);
		context.setAttribute(UserService.class.getName(), userService);

	}

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		ServletContextListener.super.contextDestroyed(contextEvent);
	}
}
