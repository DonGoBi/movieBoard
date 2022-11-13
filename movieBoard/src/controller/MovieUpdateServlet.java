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

@WebServlet("/movieUpdate.do")
public class MovieUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//영화정보 수정을 위한 폼으로 이동 처리하는 서블릿 클래스
		String code=request.getParameter("code");
		MovieDAO mDao =MovieDAO.getInstance();
		
		MovieVO mVo=mDao.selectMovieByCode(code);
		request.setAttribute("movie", mVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("movieUpdate.jsp");
		dispatcher.forward(request, response);
	}

	//영화정보수정을 위한 서블릿
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		ServletContext context=getServletContext();
		String path=context.getRealPath("images");
		
		int sizeLimit=10*1024*1024;
		String encType="utf-8";
		
		MultipartRequest multi = new MultipartRequest(
				request,
				path,
				sizeLimit,
				encType,
				new DefaultFileRenamePolicy()
		);
		
		//폼에서 입력한 영화정보 얻어오기
		String code=multi.getParameter("code");
		String title=multi.getParameter("title");
		int price=Integer.parseInt(multi.getParameter("price"));
		String director=multi.getParameter("director");
		String actor=multi.getParameter("actor");
		String poster=multi.getFilesystemName("poster");
		if(poster == null) {
			poster = multi.getParameter("nomakeImg");
		}
		
		String synopsis=multi.getParameter("synopsis");
		
		//상품정보를 저장할 객체 생성
		MovieVO mVo=new MovieVO();
		mVo.setCode(Integer.parseInt(code));
		mVo.setTitle(title);
		mVo.setPrice(price);
		mVo.setDirector(director);
		mVo.setActor(actor);
		mVo.setPoster(poster);
		mVo.setSynopsis(synopsis);

		MovieDAO mDao= MovieDAO.getInstance();
		mDao.updateMovie(mVo);
		
		response.sendRedirect("movieList.do");
		
	}

}
