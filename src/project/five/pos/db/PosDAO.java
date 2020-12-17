package project.five.pos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			String today =String.format("\'%%%s%%\'", new Day().TodayYmd());
			ps = conn.prepareStatement("select *"
									+ " from payment"
									+ " where payment_date like " + today
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
			String sql = "";
//			SimpleDateFormat simple = new SimpleDateFormat("yy/MM/dd");
//			Date now = new Date();
//			String today = simple.format(now);
			
			if (column_name.equals("payment_date")) {
				sql = "select * from payment where " + column_name 
							+ " like \'%"+ column_data + "%\' order by payment_date asc";
			} else if (column_name.equals("actual_expenditure")) {
				sql = "select * from payment where " + column_name + " >= " + column_data 
									+ " order by actual_expenditure desc";
			} else {
				sql = "select * from payment "
						+ "where " + column_name + " = \'" + column_data + "\'";
			}

			System.out.println(column_data);
			System.out.println(sql);
			ps = conn.prepareStatement(sql);

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
	
	/*
	 	한달전 데이터 지우는 메서드
	 */
	public void deleteAmonthAgoDate() {
	
		conn = DBManager.getConnection();

		try {
			
			// 테스트 끝나면 지우기
			conn.setAutoCommit(false);
			
			String amonth_ago = new Day().AmonthAgoYmd();
			String sql = "delete from cart where saled_date like \'%" + amonth_ago + "%\'";
			
			ps = conn.prepareStatement(sql);
			
			int check = ps.executeUpdate();
			
			System.out.println("오래된 데이터 지우기 쿼리 : " + sql);
			System.out.println(amonth_ago + "날의 데이터 "+ check +"행이 삭제되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args) {
//		PosDAO pos = new PosDAO();
//		pos.deleteAmonthAgoDate();
//	}
}
