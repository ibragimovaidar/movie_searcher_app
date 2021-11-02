package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl.ImagesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "imagesServlet", urlPatterns = "/images/*")
public class ImagesServlet extends HttpServlet {

	private ImagesService imagesService;

	@Override
	public void init(ServletConfig config) {
		imagesService = ((ImagesService) config.getServletContext().getAttribute(ImagesService.class.getName()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageUUID = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
		int height = Integer.parseInt(req.getParameter("h"));
		int width = Integer.parseInt(req.getParameter("w"));

		resp.setContentType("image/jpg");

		try (InputStream resizedImage = imagesService.getResizedImage(imageUUID, height, width);
			 OutputStream outputStream = resp.getOutputStream()
		){
			resp.setContentLength(resizedImage.available());
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = resizedImage.read(buf)) >= 0) {
				outputStream.write(buf, 0, count);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}


}
