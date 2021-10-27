package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(filterName = "charsetEncodingFilter", urlPatterns = "/*")
public class CharsetEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		chain.doFilter(request, response);
	}
}
