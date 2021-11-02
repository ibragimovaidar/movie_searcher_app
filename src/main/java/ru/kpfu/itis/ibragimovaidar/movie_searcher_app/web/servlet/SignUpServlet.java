package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserSignUpForm;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

	private UserService userService;

	@Override
	public void init(ServletConfig servletConfig){
		ServletContext context = servletConfig.getServletContext();
		userService = (UserService) context.getAttribute(UserService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("signUp.ftl").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserSignUpForm userSignUpForm = new UserSignUpForm(
				req.getParameter("username"),
				req.getParameter("password"),
				req.getParameter("description"),
				req.getParameter("email"),
				req.getParameter("firstName"),
				req.getParameter("lastName"),
				LocalDate.parse(req.getParameter("dateOfBirth"))
		);
		userService.createUser(userSignUpForm);
		resp.sendRedirect("/signIn");
	}
}
