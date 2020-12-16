package project.five.pos.payment.swing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import project.five.pos.db.DBManager;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;

public class PaymentQuery {
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	LocalDate today;
	int actual_expenditure;
	int couponNo;
	int device_id;

	
	public PaymentQuery(LocalDate today, int actual_expenditure, int couponNo, int device_id) {
		this.today = today;
		this.actual_expenditure = actual_expenditure;
		this.couponNo = couponNo;
		this.device_id = device_id;
	
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO payment VALUES (payy_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, ClickedBtnAction.getPaymentType().toString());
			ps.setString(2, today.toString());
			ps.setString(3, PaidByCard.cardId);
			ps.setString(4, PaidByCard.cardNumber);
			ps.setInt(5, PaidByCash.i_money);
			ps.setInt(6, actual_expenditure);
			ps.setInt(7, couponNo);
			ps.setInt(8, device_id);
			
			rs = ps.executeQuery();
			
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			
			PaidByCard.cardId = "";
			PaidByCard.cardNumber = "";
			PaidByCash.i_money = 0;				
			couponNo = 0;
			
		} catch (SQLException e1) {					
			e1.printStackTrace();
		}
	}
	
	
}
