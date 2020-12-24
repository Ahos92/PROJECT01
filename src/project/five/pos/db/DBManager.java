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
			System.out.println("DB�� ����Ǿ����ϴ�.!!");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}

	// db ��������
	public static void p_c_Close(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps != null)
			ps.close();
		if(conn != null)
			conn.close();
		System.out.println("DB ����!");
	}

	// db ��������
	public static void r_p_c_Close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
		System.out.println("DB ����!");

	} 

	public static void p2_r_p_c_Close(PreparedStatement ps2, ResultSet rs, PreparedStatement ps,  Connection conn) throws SQLException {
		if (ps2 != null)
			ps2.close();
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();	
		if (conn != null)
			conn.close();
		System.out.println("DB ����!");

	} 

	public static void p2_p_c_Close(PreparedStatement ps2, PreparedStatement ps,  Connection conn) throws SQLException {
		if (ps2 != null)
			ps2.close();
		if (ps != null)
			ps.close();	
		if (conn != null)
			conn.close();
		System.out.println("DB ����!");

	} 
	
}

