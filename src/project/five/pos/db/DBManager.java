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
			
			// 标情标情标情 
			System.out.println("DB俊 楷搬登菌嚼聪促.!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return conn;
	}




}
