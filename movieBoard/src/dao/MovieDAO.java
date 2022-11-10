package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MovieVO;
import util.DBManager;

public class MovieDAO {

	private MovieDAO() {}
	
	private static MovieDAO instance = new MovieDAO();
	
	public static MovieDAO getInstance() {
		return instance;
	}
	
	//영화 리스트 조회 메소드 생성
		public List<MovieVO> selectAllMovies(){
			
			String sql="select * from movie order by code desc";	//최근등록한 상품 위쪽에 나열
			List<MovieVO> list = new ArrayList<MovieVO>();
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try {
				conn=DBManager.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {		//행단위로 처리
					
					MovieVO mVo=new MovieVO();
					mVo.setCode(rs.getInt("code"));
					mVo.setTitle(rs.getString("title"));
					mVo.setPrice(rs.getInt("price"));
					mVo.setDirector(rs.getString("director"));
					mVo.setActor(rs.getString("actor"));
					mVo.setPoster(rs.getString("poster"));
					mVo.setSynopsis(rs.getString("synopsis"));
					
					list.add(mVo);		//mVo에 리스트추가명령
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn,pstmt,rs);
			}
			return list;
		}
		
		//영화등록 클릭시 DB에 상품 추가 등록 메소드 생성
		public void insertMovie(MovieVO mVo) {			//MovieVO의 객체 mVo를 매개변수로 하는 메소드
			
			Connection conn=null;
			PreparedStatement pstmt = null;
			
			String sql="insert into movie values(movie_seq.NEXTVAL,?,?,?,?,?,?)";
			
			try {
				conn=DBManager.getConnection();
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, mVo.getTitle());
				pstmt.setInt(2, mVo.getPrice());
				pstmt.setString(3, mVo.getDirector());
				pstmt.setString(4, mVo.getActor());
				pstmt.setString(5, mVo.getPoster());
				pstmt.setString(6, mVo.getSynopsis());
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				DBManager.close(conn, pstmt);
			}
			
		}
		
		
		
		//영화정보수정을 위해 code로 해당영화를 찾아 영화정보를 MovieVO 객체로 얻어주는 메소드
		public MovieVO selectMovieByCode(String code) {
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			MovieVO mVo=null;
			String sql="select * from movie where code=?";
			
			try {
				conn=DBManager.getConnection();
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, code);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					mVo=new MovieVO();
					
					mVo.setCode(rs.getInt("code"));
					mVo.setTitle(rs.getString("title"));
					mVo.setPrice(rs.getInt("price"));
					mVo.setDirector(rs.getString("director"));
					mVo.setActor(rs.getString("actor"));
					mVo.setPoster(rs.getString("poster"));
					mVo.setSynopsis(rs.getString("synopsis"));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				DBManager.close(conn, pstmt, rs);
			}
			return mVo;
		}
		
		
		
		
		// 얻어온 영화정보 수정을 위한 메소드 추가
		public void updateMovie(MovieVO mVo) {
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			
			
			String sql="update movie set title=?, price=?, director=?, actor=?, poster=?, synopsis=? where code=?";
			
			try {
				conn=DBManager.getConnection();
				pstmt=conn.prepareStatement(sql);
				
				
				pstmt.setString(1, mVo.getTitle());
				pstmt.setInt(2, mVo.getPrice());
				pstmt.setString(3, mVo.getDirector());
				pstmt.setString(4, mVo.getActor());
				pstmt.setString(5, mVo.getPoster());
				pstmt.setString(6, mVo.getSynopsis());
				pstmt.setInt(7, mVo.getCode());

				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt);
			}
			
		}
		
		//얻어온 영화정보 삭제를 위한 메소드 생성
		public void deleteMovie(String code) {
			
			String sql="delete movie where code=?";
			
			Connection conn=null;
			PreparedStatement pstmt = null;
			
			try {
				conn=DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt);
			}
			
			
		}
		
		
	
	
	
	
	
	
	
}
