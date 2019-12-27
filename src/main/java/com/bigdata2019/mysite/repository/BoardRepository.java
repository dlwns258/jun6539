package com.bigdata2019.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bigdata2019.mysite.vo.BoardVo;

public class BoardRepository {
	
	public Boolean write(BoardVo vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
	
			//SQL 준비
			String sql = 
				" insert" + 
				"   into board" + 
				" values (null, ?, ?, ?,  now())";
					
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		//1. JDBC Driver(Mysql) 로딩
		Class.forName("com.mysql.jdbc.Driver");
		
		//2. 연결하기
		String url = "jdbc:mysql://localhost:3306/webdb";
		Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
		
		return conn;
	}	
}
