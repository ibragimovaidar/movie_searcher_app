package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.web.servlet;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.UserDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl.ImagesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "imagesUploadServlet", urlPatterns = "/upload")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1 MB
		maxFileSize = 1024 * 1024 * 10,      // 10 MB
		maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ImagesUploadServlet extends HttpServlet {

	private ImagesService imagesService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		imagesService = (ImagesService) config.getServletContext().getAttribute(ImagesService.class.getName());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("upload.ftl").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part imageFilePart = request.getPart("file");
		Integer userId = ((UserDTO) request.getSession().getAttribute("userDTO")).getId();
		try {
			imagesService.saveImage(userId, imageFilePart);
		} catch (ServiceException e) {
			throw new ServletException(e);
		}
		response.sendRedirect("/profile");
	}
}
