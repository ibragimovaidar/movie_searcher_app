package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception.WrongUserOrPasswordException;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.LightUserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserSignInForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.UserService;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {

	private UserService userService;

	@Override
	public void init(ServletConfig servletConfig){
		ServletContext context = servletConfig.getServletContext();
		userService = (UserService) context.getAttribute(UserService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.ftl").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		HttpSession session = req.getSession(true);

		UserSignInForm userSignInForm = new UserSignInForm(
				req.getParameter("username"),
				req.getParameter("password")
		);

		UserDTO userDTO = userService.authenticateUser(userSignInForm)
				.orElseThrow(WrongUserOrPasswordException::new);
		session.setAttribute("userDTO", userDTO);
		session.setAttribute("lightUserDTO", LightUserDTO.from(userDTO));
		session.setAttribute("authorized", true);
		resp.sendRedirect("/profile?username=" + userDTO.getUsername());
	}
}
