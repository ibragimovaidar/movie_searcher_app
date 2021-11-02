package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieCommentaryForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieCommentaryService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "movieCommentaryServlet", urlPatterns = "/movieCommentary")
public class MovieCommentaryServlet extends HttpServlet {

	private MovieCommentaryService movieCommentaryService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		movieCommentaryService =
				(MovieCommentaryService) servletContext.getAttribute(MovieCommentaryService.class.getName());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieCommentaryForm movieCommentaryForm = new MovieCommentaryForm(
				req.getParameter("text"),
				Integer.parseInt(req.getParameter("ownerId")),
				Integer.parseInt(req.getParameter("movieId"))
		);
		movieCommentaryService.createCommentary(movieCommentaryForm);
		resp.sendRedirect("/movie/" + movieCommentaryForm.getMovieId());
	}
}
