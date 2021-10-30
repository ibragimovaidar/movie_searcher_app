package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.MovieDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.MovieService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "movieServlet", urlPatterns = "/movie/*")
public class MovieServlet extends HttpServlet {

	private MovieService movieService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext servletContext = config.getServletContext();
		movieService = (MovieService) servletContext.getAttribute(MovieService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer movieId = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1));
		MovieDTO movieDTO = movieService.findById(movieId).orElseThrow(ServletException::new);

		req.setAttribute("userDTO", null);
		if (req.getSession().getAttribute("authorized") != null &&
				(Boolean) req.getSession().getAttribute("authorized").equals(true)){
			UserDTO userDTO = (UserDTO) req.getSession(false).getAttribute("userDTO");
			req.setAttribute("userDTO", userDTO);
			req.setAttribute("authorized", true);
		}
		req.setAttribute("movieDTO", movieDTO);
		getServletContext().getRequestDispatcher("/mPage.ftl").forward(req,resp);
	}
}
