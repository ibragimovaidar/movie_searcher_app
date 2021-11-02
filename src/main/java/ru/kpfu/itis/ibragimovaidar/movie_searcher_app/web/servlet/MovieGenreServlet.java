package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieGenreDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.MovieGenreRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieGenreService;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "movieGenreServlet", urlPatterns = "/genres")
public class MovieGenreServlet extends HttpServlet {

	private MovieGenreService movieGenreService;
	private MovieService movieService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		movieGenreService = (MovieGenreService) servletContext.getAttribute(MovieGenreService.class.getName());
		movieService = (MovieService) servletContext.getAttribute(MovieService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null){
			List<MovieGenreDTO> movieGenres = movieGenreService.findAll();
			request.setAttribute("movieGenres", movieGenres);
			request.getRequestDispatcher("genres.ftl").forward(request, response);
			return;
		}
		Integer movieGenreId = Integer.parseInt(request.getParameter("id"));
		MovieGenreDTO movieGenreDTO = movieGenreService.findById(movieGenreId).orElseThrow(ServletException::new);
		List<MovieDTO> movies = movieService.findByMovieGenreId(movieGenreId);
		request.setAttribute("movieGenreDTO", movieGenreDTO);
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("mgenre.ftl").forward(request, response);
	}
}
