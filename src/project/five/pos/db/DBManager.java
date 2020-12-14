package project.five.pos.db;

import java.sql.*;

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

	// db 연결해제
	public static void p_c_Close(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps != null)
			ps.close();
		if(conn != null)
			conn.close();
		System.out.println("DB 종료!");
	}

	// db 연결해제
	public static void p_r_c_Close(PreparedStatement ps, ResultSet rs, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
		System.out.println("DB 종료!");

	} 


}

