package project.five.pos.payment.swing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.five.pos.db.DBManager;

public class MembershipQuery {

	static Connection conn;
	static PreparedStatement ps;
	static PreparedStatement ps2;
	static PreparedStatement ps3;
	static ResultSet rs;
	
	int actual_expenditure;
	int ml_as_you_wish;
	
	public MembershipQuery(int actual_expenditure) {
		this.actual_expenditure = actual_expenditure;
		
		try {
			
			conn = DBManager.getConnection();
			
			// 오토 커밋 해제
			conn.setAutoCommit(false);
			
			String sql = "UPDATE customer SET amount_price = amount_price + ?, mileage = mileage + ? WHERE contact_no = ?";
									
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, actual_expenditure);
			ps.setDouble(2, (actual_expenditure * CheckMem.memberPct));
			ps.setString(3, CheckMem.mobileNo);
									
			int row = ps.executeUpdate();
			// 테스트 코드
			System.out.println(row + "행이 변했습니다.");
			
			String sql2 = "SELECT membership, accumulation_pct, amount_price FROM customer WHERE contact_no =?";
			
			ps2 = conn.prepareStatement(sql2);
			
			ps2.setString(1, CheckMem.mobileNo);
			
			ResultSet rs = ps2.executeQuery();
			
			int amountPrice = 0;
			double pct = 0;
			String member = "";
			if(rs.next()) {
				member = rs.getString("membership");
				pct = rs.getDouble("accumulation_pct");
				amountPrice = rs.getInt("amount_price");
			}
			
			if(amountPrice >= 1000000) {
				member = "diamond";
				pct = 0.05;
			}
			else if(amountPrice >= 500000) {
				member = "platinum";
				pct = 0.04;
			}
			else if(amountPrice >= 250000) {
				member = "gold";
				pct = 0.03;
			}
			else if(amountPrice >= 100000) {
				member = "silver";
				pct = 0.02;
			}
			String sql3 = "UPDATE customer SET membership = ?, accumulation_pct = ? WHERE contact_no = ?";
			
			ps3 = conn.prepareStatement(sql3);
			
			ps3.setString(1, member);
			ps3.setDouble(2, pct);
			ps3.setString(3, CheckMem.mobileNo);
														
			int row3 = ps3.executeUpdate();
			// 테스트 코드
			System.out.println(row3 + "행이 변했습니다.");
			
			conn.commit();
						
			
												
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
				try {
					if(ps != null) ps.close();
					if(ps2 != null) ps2.close();
					if(ps3 != null) ps3.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	public MembershipQuery(int actual_expenditure, int ml_as_you_wish) {
		this.actual_expenditure = actual_expenditure;
		this.ml_as_you_wish = ml_as_you_wish;
		
		try {
			
			conn = DBManager.getConnection();
			
			// 오토 커밋 해제
			conn.setAutoCommit(false);
			
			String sql = "UPDATE customer SET amount_price = amount_price + ?, mileage = mileage - ?  + ? WHERE contact_no = ?";
									
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, actual_expenditure);
			ps.setInt(2, ml_as_you_wish);
			ps.setDouble(3, (actual_expenditure * CheckMem.memberPct));			
			ps.setString(4, CheckMem.mobileNo);
									
			int row = ps.executeUpdate();
			// 테스트 코드
			System.out.println(row + "행이 변했습니다.");
			
			String sql2 = "SELECT membership, accumulation_pct, amount_price FROM customer WHERE contact_no =?";
			
			ps2 = conn.prepareStatement(sql2);
			
			ps2.setString(1, CheckMem.mobileNo);
			
			ResultSet rs = ps2.executeQuery();
			
			int amountPrice = 0;
			double pct = 0;
			String member = "";
			if(rs.next()) {
				member = rs.getString("membership");
				pct = rs.getDouble("accumulation_pct");
				amountPrice = rs.getInt("amount_price");
			}
			
			if(amountPrice >= 1000000) {
				member = "diamond";
				pct = 0.05;
			}
			else if(amountPrice >= 500000) {
				member = "platinum";
				pct = 0.04;
			}
			else if(amountPrice >= 250000) {
				member = "gold";
				pct = 0.03;
			}
			else if(amountPrice >= 100000) {
				member = "silver";
				pct = 0.02;
			}
			String sql3 = "UPDATE customer SET membership = ?, accumulation_pct = ? WHERE contact_no = ?";
			
			ps3 = conn.prepareStatement(sql3);
			
			ps3.setString(1, member);
			ps3.setDouble(2, pct);
			ps3.setString(3, CheckMem.mobileNo);
														
			int row3 = ps3.executeUpdate();
			// 테스트 코드
			System.out.println(row3 + "행이 변했습니다.");
			
			conn.commit();
						
			if(ps != null) ps.close();
			if(ps2 != null) ps2.close();
			if(ps3 != null) ps3.close();
			if(conn != null) conn.close();
												
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
}
