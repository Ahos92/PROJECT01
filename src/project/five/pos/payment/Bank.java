package project.five.pos.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.five.pos.db.DBManager;

public class Bank {

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public Bank() {
		
		
	}
		
	public void selectAllBank() {
		try {
			conn = DBManager.getConnection();

			ps = conn.prepareStatement("select * from bank");

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.printf("%-15s\t%-10s\t%-30s\t%-10s\n",
						rs.getString("bank_id"),
						rs.getString("bank_name"),
						rs.getString("bank_location"),
						rs.getString("phone_number")
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
		Bank bk = new Bank();
		bk.selectAllBank();
	}
}
