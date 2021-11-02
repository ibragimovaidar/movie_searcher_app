package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.ReviewModalDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.ReviewService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "reviewServlet", urlPatterns = "/review")
public class ReviewServlet extends HttpServlet {

	private ReviewService reviewService;
	private ObjectMapper objectMapper;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		reviewService = (ReviewService) servletContext.getAttribute(ReviewService.class.getName());
		objectMapper = (ObjectMapper) servletContext.getAttribute(ObjectMapper.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		ReviewModalDTO reviewModalDTO = reviewService.getReviewModalDTO(id).orElseThrow(ServletException::new);

		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType("application/json");
		try (PrintWriter writer = response.getWriter()){
			writer.write(objectMapper.writeValueAsString(reviewModalDTO));
		}
	}

}
