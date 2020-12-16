package project.five.pos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PosDAO {

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	PosVO pos;
	ArrayList<PosVO> poslist;

	public PosDAO() {}
	
	public ArrayList<PosVO> searchAllPayment() {

		poslist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
									+ " from payment"
									+ " order by payment_no asc");

			rs = ps.executeQuery();

			while (rs.next()) {
				pos = new PosVO();
				
				pos.setPayment_no(rs.getInt("payment_no"));
				pos.setPayment_type(rs.getString("payment_type"));
				pos.setPayment_date(rs.getString("payment_date"));
				pos.setBank_id(rs.getString("bank_id"));
				pos.setCard_num(rs.getString("card_num"));
				pos.setAmount_of_money(rs.getInt("amount_of_money"));
				pos.setActual_expenditure(rs.getInt("actual_expenditure"));
				pos.setCoupon_no(rs.getInt("coupon_no"));
				
				poslist.add(pos);
			}
		
			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return poslist;

	}
	
	
	public ArrayList<PosVO> searchPayment(String column_name, String column_data) {

		poslist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select * "
										+ "from payment "
										+ "where " + column_name + " = " + column_data);

			rs = ps.executeQuery();

			while (rs.next()) {
				pos = new PosVO();
				
				pos.setPayment_no(rs.getInt("payment_no"));
				pos.setPayment_type(rs.getString("payment_type"));
				pos.setPayment_date(rs.getString("payment_date"));
				pos.setBank_id(rs.getString("bank_id"));
				pos.setCard_num(rs.getString("card_num"));
				pos.setAmount_of_money(rs.getInt("amount_of_money"));
				pos.setActual_expenditure(rs.getInt("actual_expenditure"));
				pos.setCoupon_no(rs.getInt("coupon_no"));
				
				poslist.add(pos);
			}
		
			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return poslist;

	}
	public static void main(String[] args) {
		PosDAO pos = new PosDAO();
		System.out.println(pos.searchPayment("payment_type", "CASH"));
	}
}
