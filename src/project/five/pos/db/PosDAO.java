package project.five.pos.db;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import project.five.pos.device.ManagerDisplay;

public class PosDAO {

	static Connection conn;
	static PreparedStatement ps, ps2;
	static ResultSet rs;

	PosVO pos;
	ArrayList<PosVO> poslist;

	String today;

	public PosDAO() {}

	// ------------------------------------------ POS���� �޼��� ----------------------------------------------
	/*
	 	�Ѵ��� ������ ����� �޼���
	 		- boolean���� ���� ó�� ����� �޾� ���� ���� ���α׷� ���� X
	 		
	 		- test : true�� ����
	 */
	public boolean deleteAmonthAgoDate() {

		conn = DBManager.getConnection();

		try {

			// �׽�Ʈ ������ �����
			conn.setAutoCommit(false);

			String amonth_ago = new Day().AmonthAgoYmd();
			String sql = "delete from cart where saled_date like \'%" + amonth_ago + "%\'";
			ps = conn.prepareStatement(sql);

			int check = ps.executeUpdate();

			System.out.println("������ ������ ����� ���� : " + sql);
			System.out.println(amonth_ago + "���� ������ "+ check +"���� �����Ǿ����ϴ�.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
//			try {
//				DBManager.p2_r_p_c_Close(ps2, rs, ps, conn);
//				
//			} catch (SQLException e) {}
		}
		
		return true;
	}

	/*
		�Ϸ� ���� ���� �޼���
			- boolean���� ���� ó�� ����� �޾� ���� ���� ���α׷� ���� X		
	 */
	public boolean saveDailyAmount() {
		conn = DBManager.getConnection();

		int total_money = 0;
		try {
			conn.setAutoCommit(false);

			today = new Day().TodayYmd();

			String sql01 = "select sum(total_price) from cart where saled_date like \'%" + today + "%\'";
			ps = conn.prepareStatement(sql01);

			rs = ps.executeQuery();

			if (rs.next()) {
				total_money = rs.getInt("sum(total_price)");

				System.out.printf("%s���� �� ���� : %d\n", today, total_money);


			} else {
				conn.rollback();
				System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.(sql01) ���翡 �������ּ���. 02-000-0000");
			}


			String sql02 = "insert into daily_sales_amount values (?, ?)";
			ps2 = conn.prepareStatement(sql02);

			ps2.setString(1, today);
			ps2.setInt(2, total_money);

			try {
				int result = ps2.executeUpdate();
				if(result != 1){
					conn.rollback();
					System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.(sql02) ���翡 �������ּ���. 02-000-0000");
				} else {
//					conn.commit();
					System.err.println("���� PosDAO().saveDailyAmount()�� commit() ������ ������ �ʽ��ϴ�!");
					System.out.println("daily_sales_amount ���̺��� " + result + "���� ���� �Ǿ����ϴ�.");
					System.out.printf(">>\tSALES_DATE : %s\t TOTAL_MONEY : %d\n", today, total_money);
					return true;
				}

			} catch (SQLIntegrityConstraintViolationException sie){
				System.err.println("TEST : "+ today +"���� �����ʹ� �̹� �ֽ��ϴ�!");
				System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.(PK) ���翡 �������ּ���. 02-000-0000");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				DBManager.p2_r_p_c_Close(ps2, rs, ps, conn);
				
			} catch (SQLException e) {}
		}
		
		return false;
	}

	/*
  	�����ϴ� ������� ���� �Ǻ�, ��� �α��� �� �� ���	  
	 */
	public boolean searchPOS(int device_id, String device_pw) {

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select * "
					+ "from pos "
					+ "where device_id = ? and device_pw = ?");

			ps.setInt(1, device_id);
			ps.setString(2, device_pw);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("DEVICE �α��� ����!");
				return true;
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
				
			} catch (SQLException e) {}
		}

		System.err.println("DEVICE �α��� ����");
		return false;
	}


	/*
		�����ϴ� ������ ���̵� ���� �Ǻ�, ��� �α��� �� �� ���  	
	 */
	public boolean searchAdmin(int business_id, String business_pw) {

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select * "
					+ "from businessadminister "
					+ "where business_id = ? and business_pw = ?");

			ps.setInt(1, business_id);
			ps.setString(2, business_pw);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("������ �α��� ����!");
				return true;
			} 
		
		} catch (SQLException e) {
			System.err.println("������ �α��� ����!");
		} finally {
			
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		System.err.println("������ �α��� ����!");
		return false;
	}


	public boolean SighUPManager(PosVO business) {
		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("insert into businessadminister "
					+ "values (?, ?, ?, ?, ?)");
			ps.setInt(1, business.getBusiness_id()); 	// business_id NUMBER(5) 
			ps.setString(2, business.getBusiness_pw()); // business_pw VARCHAR2(20)
			ps.setString(3, business.getB_last_name()); 	// last_name VARCHAR2(20)
			ps.setString(4, business.getB_first_name());	// first_name VARCHAR2(20)
			ps.setString(5, business.getB_contact_no());	// contact_no VARCHAR2(30)

			try {
				ps.addBatch();

			} catch (BatchUpdateException e) {
				System.err.println("���̵� �̹� �ֽ��ϴ�.");
				return false;
			}

			try {
				ps.executeBatch();

			} catch (BatchUpdateException e) {
				System.err.println("��� �׸��� ��Ȯ�ϰ� �Է����ּ���!");
				e.printStackTrace();
				return false;
			}

			System.out.println("ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.p_c_Close(ps, conn);
			} catch (SQLException e) {}
		}

		return false;
	}

	// ------------------------------------------ ���� ���� �޼��� ----------------------------------------------
	/*
	 	���� ���� ���� ��ȸ
	 */
	public ArrayList<PosVO> searchAllPayment() {

		poslist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			today =String.format("\'%%%s%%\'", new Day().TodayYmd());
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return poslist;

	}

	/*
	 	���� ���� �˻� ��ȸ
	 */
	public ArrayList<PosVO> searchPayment(String column_name, String column_data) {

		poslist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			String sql = "";
			//			SimpleDateFormat simple = new SimpleDateFormat("yy/MM/dd");
			//			Date now = new Date();
			//			String today = simple.format(now);
			today = String.format("%s%%", new Day().TodayYmd());

			if (column_name.equals("payment_date")) {
				sql = "select * from payment where " + column_name 
						+ " like \'%"+ today + column_data + ":%\' order by payment_date asc";
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
				pos.setPayment_date(rs.getString("payment_date").trim());
				pos.setBank_id(rs.getString("bank_id"));
				pos.setCard_num(rs.getString("card_num"));
				pos.setAmount_of_money(rs.getInt("amount_of_money"));
				pos.setActual_expenditure(rs.getInt("actual_expenditure"));
				pos.setCoupon_no(rs.getInt("coupon_no"));

				poslist.add(pos);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return poslist;

	}

	//	public static void main(String[] args) {
	//		PosDAO pos = new PosDAO();
	//		pos.deleteAmonthAgoDate();
	//	}
}
