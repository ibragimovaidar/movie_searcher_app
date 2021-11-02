package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/"})
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		if (session.isNew() ||
				session.getAttribute("authenticated") == null ||
				session.getAttribute("userDTO") == null ||
				session.getAttribute("lightUserDTO")  == null
		){
			session.setAttribute("authenticated", false);
			request.setAttribute("authenticated", false);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
