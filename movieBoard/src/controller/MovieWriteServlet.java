package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.MovieDAO;
import dto.MovieVO;


@WebServlet("/movieWrite.do")
public class MovieWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("movieWrite.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//영화등록을 위한 서블릿
		request.setCharacterEncoding("utf-8");
		
		ServletContext context=getServletContext();
		String uploadFilePath=context.getRealPath("images");
		
		int sizeLimit=10*1024*1024;
		String encType="utf-8";
		
		MultipartRequest multi = new MultipartRequest(
				request,
				uploadFilePath,
				sizeLimit,
				encType,
				new DefaultFileRenamePolicy()
				
		);
		
		String title=multi.getParameter("title");
		int price=Integer.parseInt(multi.getParameter("price"));
		String director=multi.getParameter("director");
		String actor=multi.getParameter("actor");
		String poster=multi.getFilesystemName("poster");
		String synopsis=multi.getParameter("synopsis");
				
		MovieVO mVo = new MovieVO();
		mVo.setTitle(title);
		mVo.setPrice(price);
		mVo.setDirector(director);
		mVo.setActor(actor);
		mVo.setPoster(poster);
		mVo.setSynopsis(synopsis);
		
		MovieDAO mDao = MovieDAO.getInstance();
		mDao.insertMovie(mVo);
		
		response.sendRedirect("movieList.do");
	}

}
