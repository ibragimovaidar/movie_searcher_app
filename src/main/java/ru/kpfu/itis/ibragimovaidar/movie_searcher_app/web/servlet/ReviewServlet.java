package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ReviewService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "reviewServlet", urlPatterns = "/review")
public class ReviewServlet extends HttpServlet {

	private ReviewService reviewService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		reviewService = (ReviewService) servletContext.getAttribute(ReviewService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		ReviewDTO reviewDTO = reviewService.findById(id).orElseThrow(ServletException::new);
		request.setAttribute("reviewDTO", reviewDTO);
		request.getRequestDispatcher("review.ftl");
	}
}
