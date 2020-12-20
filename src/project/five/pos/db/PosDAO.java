package project.five.pos.db;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import project.five.pos.device.ManagerDisplay;

public class PosDAO {

	static Connection conn;
	static PreparedStatement ps, ps2;
	static ResultSet rs;

	PosVO pos;

	String today;

	public PosDAO() {}

	// DEVICE ----------------------------------------------------------------------------------------
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

			String amonth_ago = new Day().AmonthAgoYmdD();
			String sql01 = "delete from cart where saled_date like \'%" + amonth_ago + "%\'";
			ps = conn.prepareStatement(sql01);

			int check01 = ps.executeUpdate();

			String sql02 = "delete from payment where payment_date like \'%" + amonth_ago + "%\'";
			ps2 = conn.prepareStatement(sql02);

			int check02 = ps2.executeUpdate();

			if (check01 == 1 && check02 == 1) {
				conn.commit();
				System.out.println("������ ������ ����� ����1 : " + sql01);
				System.out.println("������ ������ ����� ����2 : " + sql02);
				System.out.println(amonth_ago + "���� ������ "+ check01 +"��� "+ check02 + "���� �����Ǿ����ϴ�.");
				return true;
			} else {
				conn.rollback();
				System.err.println("deleteAmonthAgoDate() ���� ���� ó���� ���� �ʾҽ��ϴ�.");
				//				return true;
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				DBManager.p2_p_c_Close(ps2, ps, conn);

			} catch (SQLException e) {}
		}

		System.err.println("���� ���� ó���� ���� �ʾҽ��ϴ�. deleteAmonthAgoDate() ���翡 �������ּ���. 02-000-0000");
		//return false;
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

			today = new Day().TodayYmdD();

			String sql01 = "select sum(total_price) from cart where saled_date like \'%" + today + "%\'";
			ps = conn.prepareStatement(sql01);

			rs = ps.executeQuery();

			if (rs.next()) {
				total_money = rs.getInt("sum(total_price)");

				System.out.printf("%s���� �� ���� : %d\n", today, total_money);


			} else {
				conn.rollback();
				System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.saveDailyAmount() (sql01) ���翡 �������ּ���. 02-000-0000");
			}


			String sql02 = "insert into daily_sales_amount values (?, ?)";
			ps2 = conn.prepareStatement(sql02);

			ps2.setString(1, today);
			ps2.setInt(2, total_money);

			try {
				int result = ps2.executeUpdate();
				if(result != 1){
					conn.rollback();
					System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.saveDailyAmount() (sql02) ���翡 �������ּ���. 02-000-0000");
				} else {
					//					conn.commit();
					System.err.println("���� PosDAO().saveDailyAmount()�� commit() ������ ������ �ʽ��ϴ�!");
					System.out.println("daily_sales_amount ���̺��� " + result + "���� ���� �Ǿ����ϴ�.");
					System.out.printf(">>\tSALES_DATE : %s\t TOTAL_MONEY : %d\n", today, total_money);
					System.out.println();
					return true;
				}

			} catch (SQLIntegrityConstraintViolationException sie){
				System.err.println("TEST : "+ today +"���� �����ʹ� �̹� �ֽ��ϴ�!");
				System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.saveDailyAmount() (PK) ���翡 �������ּ���. 02-000-0000");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				DBManager.p2_r_p_c_Close(ps2, rs, ps, conn);

			} catch (SQLException e) {}
		}

		System.err.println("�������� ó���� ���� �ʾҽ��ϴ�. saveDailyAmount() ���翡 �������ּ���. 02-000-0000");
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



	// CART ------------------------------------------------------------------------------------------
	/*
	 * 	������ ���� â���� �Ѱ� ���� ������ ����ֱ�
	 */
	public boolean saveCartlist(LocalDateTime date, int order_no, ArrayList<String> name_list, 
									ArrayList<PosVO> cart_list, int device_id) {

		conn = DBManager.getConnection();

		try {
			String sql = "insert into cart "
					+ "values(?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sql);
			String today = new Day().TodayYmdT(date);
			
			for (int j = 0; j < cart_list.size(); j++) {
				String division = String.format(" (%d)", j);
				
				ps.setString(1, today + division); 					// saled_date  - ��¥�޾Ƽ� ��ȯ
				ps.setInt(2, order_no); 							// order_no    - �Ű����� order_no
				ps.setString(3, name_list.get(j)); 					// sale_product_name - �̸���ü�޾Ƽ� �״�� �Է�
				ps.setInt(4, cart_list.get(j).getSelected_item()); // selected_item	- �Ű����� date
				ps.setInt(5, cart_list.get(j).getTotal_price()); 	// total_price	- ��ü�޾Ƽ� ����
				ps.setInt(6, device_id); 							// device_id	- �Ű����� device_id
			
				ps.addBatch();
			}
			
			System.out.println(name_list.get(0));
			System.out.println(cart_list.get(0).getSelected_item());
			System.out.println(cart_list.get(0).getTotal_price());
			
			int[] rows = ps.executeBatch();
			if (rows.length <= 0) {
				return false;
			} else {
				System.out.println("cart TABLE�� " + rows.length + "���� ����Ǿ����ϴ�.");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}
		
		return false;
	}


	// ��ȸ ���� �޼��� -----------------------------------------------------------------------------------------
	/*
 		cart TABLE ��� ���� ��ȸ
	 */
	public ArrayList<PosVO> searchAllCart() {

		ArrayList<PosVO> cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
					+ " from cart");
			rs = ps.executeQuery();

			while (rs.next()) {
				pos = new PosVO();

				pos.setSaled_date(rs.getString("saled_date"));
				pos.setOrder_no(rs.getInt("order_no"));
				pos.setSaled_prdouct_name(rs.getString("saled_product_name"));
				pos.setSelected_item(rs.getInt("selected_item"));
				pos.setTotal_price(rs.getInt("total_price"));
			
				cartlist.add(pos);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}

		}

		return cartlist;

	}


	/*
 		���ǿ� �´� cart TABLE ���� ������
 			- ���� ���� �÷� �̸�, ��
 			- ���ǿ� �´� ��ǰ ������ ���� ��ü ��ȯ 
	 */
	public ArrayList<PosVO> searchCart(String column_name, String column_data) {

		ArrayList<PosVO> cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			if (column_name.equals("saled_product_name")) {
				column_data = "\'%" + column_data + "%\'";
			} 
			
			String sql = "select *"
					+ " from cart"
					+ " where " + column_name + " like " + column_data
					+ " order by saled_date asc";
			ps = conn.prepareStatement(sql);
			
			try {
				rs = ps.executeQuery();
			} catch (SQLSyntaxErrorException sse) {
				System.err.println("�߸��� �Է�");
			}
			while (rs.next()) {
				pos = new PosVO();

				pos.setSaled_date(rs.getString("saled_date"));
				pos.setOrder_no(rs.getInt("order_no"));
				pos.setSaled_prdouct_name(rs.getString("saled_product_name"));
				pos.setSelected_item(rs.getInt("selected_item"));
				pos.setTotal_price(rs.getInt("total_price"));

				cartlist.add(pos);
			}


		} catch (SQLException e) {
			System.err.println("�߸��� �Է�");
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return cartlist;

	}


	/*
 		�ֽ� �ֹ���ȣ
	 */ 
	public int MaxOrderNumber() {

		int max = 0;

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select max(order_no) from cart");

			rs = ps.executeQuery();

			while (rs.next()) {
				max = rs.getInt("max(order_no)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}

		}

		return max;
	}


	
	// PAYMENT ---------------------------------------------------------------------------------------------- 
	/*
	 	���� ���� ���� ��ȸ
	 */
	public ArrayList<PosVO> searchAllPayment() {

		ArrayList<PosVO> poslist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select * from payment");

			rs = ps.executeQuery();

			while (rs.next()) {
				pos = new PosVO();

				pos.setPayment_date(rs.getString("payment_date"));
				pos.setPayment_type(rs.getString("payment_type"));
				pos.setBank_id(rs.getString("bank_id"));
				pos.setCard_num(rs.getString("card_num"));
				pos.setUsage_of_milage(rs.getInt("usage_of_milage"));
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

		ArrayList<PosVO> poslist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			String sql = "";
			today = String.format("%s%%", new Day().TodayYmdD());

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

				pos.setPayment_date(rs.getString("payment_date"));
				pos.setPayment_type(rs.getString("payment_type"));
				pos.setBank_id(rs.getString("bank_id"));
				pos.setCard_num(rs.getString("card_num"));
				pos.setUsage_of_milage(rs.getInt("usage_of_milage"));
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
