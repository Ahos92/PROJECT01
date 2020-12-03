package project.five.pos.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import project.five.pos.db.DBManager;

public class Customer {

	// developer merge
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	// customer_no, last_name, first_name, contact_no, membership, discount_pct
	public Customer() {

	}

	// ¸â¹öÁ¶È¸
	public void selectAllCustomer() {
	
		try {
			conn = DBManager.getConnection();

			ps = conn.prepareStatement("select * from customer");

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.printf("%d\t%s\t%s\t%s\t%s\t%.2f",
						rs.getInt("customer_no"),
						rs.getString("last_name"),
						rs.getString("first_name"),
						rs.getString("contact_no"),
						rs.getString("membership"),
						rs.getFloat("discount_pct")
						);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Customer customer = new Customer();
		customer.selectAllCustomer();
		
	}
 
}
