package project.five.pos.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBManager {

	// DBpool
	public static Connection getConnection() {
		HikariConfig config =  new  HikariConfig("hikari.properties");
		HikariDataSource ds =  new  HikariDataSource(config);
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			
			// ���������� 
			System.out.println("DB�� ����Ǿ����ϴ�.!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return conn;
	}




}
