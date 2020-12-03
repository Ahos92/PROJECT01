package project.five.pos.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBManager {

	// DBpool
	public static Connection getConnection() {
		HikariConfig config =  new  HikariConfig("some/hikari.properties");
		HikariDataSource ds =  new  HikariDataSource(config);

		Connection conn = null;
		try {
			conn = ds.getConnection();

			System.out.println("DB�� ����Ǿ����ϴ�.!!");

		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		return conn;
	}


	public static void main(String[] args) {
		getConnection();
	}

}
