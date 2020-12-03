package project.five.pos.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.five.pos.db.DBManager;

public class Customer {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	// customer_no, last_name, first_name, contact_no, membership, discount_pct
	public Customer() {
		
	}
	
	// ¸â¹öÁ¶È¸ ddd
	public void selectAllCustomer() {
		conn = DBManager.getConnection();
		
		try {
			ps = conn.prepareStatement("select * from customer");
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 
}
