package project.five.pos.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBManager {

	// DBpool
	public static Connection getConnection() {
		HikariConfig config = new HikariConfig("some/hikari.properties");
		HikariDataSource ds = new HikariDataSource(config);
		
		Connection conn = null;
		
			try {
				conn = ds.getConnection();
				// 테스트 용 코드
				//System.out.println("DB에 연결되었습니다.!!");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return conn;
	}

	public static void main(String[] args) {
		getConnection();
	}

}
