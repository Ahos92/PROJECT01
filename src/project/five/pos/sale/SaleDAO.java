package project.five.pos.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.five.pos.db.DBManager;

public class Sale {

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public Sale() {
		
	}
	
	public void showCart() {
		
		try {
			conn = DBManager.getConnection();

			ps = conn.prepareStatement("select cart_no, product_name||' '||termsofcondition as menu, \r\n"
					+ "selected_item, product_price* selected_item as price \r\n"
					+ "from cart c inner join product p using(product_no)");

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.printf("%-3d\t%-10s\t%-3d\t%-10d\n",
							rs.getInt("cart_no"),
							rs.getString("menu"),
							rs.getInt("selected_item"),
							rs.getInt("price")
							);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Sale s = new Sale();
		s.showCart();
	}
	
}
