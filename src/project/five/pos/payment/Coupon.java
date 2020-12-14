package project.five.pos.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.five.pos.db.DBManager;

public class Coupon {

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public Coupon() {
		
		
	}
		
		public void selectAllCoupon() {
			try {
				conn = DBManager.getConnection();

				ps = conn.prepareStatement("select * from coupon");

				rs = ps.executeQuery();

				while (rs.next()) {
					System.out.printf("%-15d\t%-10s\t%-10d\t%-10s\t%-10s\n",
							rs.getInt("coupon_no"),
							rs.getString("coupon_name"),
							rs.getInt("coupon_price"),
							rs.getString("start_date"),
							rs.getString("expired_date")
							
							);
				}
				rs.close();
				ps.close();
				conn.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		Coupon cp = new Coupon();
		cp.selectAllCoupon();
	}
}
