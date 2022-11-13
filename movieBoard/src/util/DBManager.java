package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	//커넥션을 얻어오는 메소드 생성
	public static Connection getConnection() throws Exception{
		
		Context initContext = new InitialContext();	
		Context envContext  = (Context)initContext.lookup("java:/comp/env");	
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");	
		Connection conn = ds.getConnection();
		
		return conn;
		
	}
	
	//select를 수행한 후 리소스 해제를 위한 메소드 생성
	//Statement객체 : 정적인 쿼리문 처리할 수있는 객체, 반드시 값이 미리 입력되어있을때 사용가능
	//PreparedStatement객체 : 쿼리문 저장
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		
		try {
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//insert,update,delete 작업을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		
		try {
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
