package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		userService = (UserService) servletContext.getAttribute(UserService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserDTO userDTO = userService.findByUsername(username).orElseThrow(ServletException::new);
		request.setAttribute("userDTO", userDTO);
		request.setAttribute("imageSrc", "/images/" + userDTO.getImageMetadata().getFolder() + "?w=256&h=256");
		request.getRequestDispatcher("profile.ftl").forward(request, response);
	}
}
