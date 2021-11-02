package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ReviewService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "reviewFormServlet", urlPatterns = "/createReview")
public class ReviewFormServlet extends HttpServlet {

	private ReviewService reviewService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		reviewService = (ReviewService) servletContext.getAttribute(ReviewService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer movieId = Integer.parseInt(request.getParameter("movieId"));
		request.setAttribute("movieId", movieId);
		request.getRequestDispatcher("reviewForm.ftl").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO userDTO = ((UserDTO) request.getSession(false).getAttribute("userDTO"));
		Integer userId = userDTO.getId();
		Integer movieId = Integer.parseInt(request.getParameter("movieId"));
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String description = request.getParameter("description");

		ReviewForm reviewForm = new ReviewForm(
				userId,
				movieId,
				rating,
				description
		);
		reviewService.create(reviewForm);
		response.sendRedirect("/profile?username=" + userDTO.getUsername());
	}
}
