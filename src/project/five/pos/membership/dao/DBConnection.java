package project.five.pos.membership.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	
	// SQL 쿼리문 작성 
	/*
	 
	 create table member(
		  id number primary key,
		  username varchar2(15),
		  password varchar2(15),
		  name varchar2(15),
		  birth varchar2(30),
		  phone varchar2(15)
	);
	
	create sequence member_seq
  		 start with 1
	 	 increment BY 1
		 maxvalue 10000;
	
	select * from member;
	  
	  
	*/
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"hr",
					"hr"
			);
			System.out.println("DB와 연결되었습니다!");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB에 연결에 실패하였습니다!");
		}
		return conn;
	}
	public static void close(Connection c, PreparedStatement p, 
			ResultSet r) {
		try {
			if(r!=null)r.close();
			if(p!=null)p.close();
			if(c!=null)c.close();
		}catch(Exception e) {}
	}
	public static void close(Connection c, PreparedStatement p) {
		try {
			if(p!=null)p.close();
			if(c!=null)c.close();
		}catch(Exception e) {}
	}

	public static void main(String[] args) {
		getConnection();
	}
}
