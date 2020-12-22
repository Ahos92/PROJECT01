package project.five.pos.device;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import project.five.pos.db.DBManager;
import project.five.pos.db.Day;
import project.five.pos.db.PosVO;
import project.five.pos.payment.swing.AskCoupon;
import project.five.pos.payment.swing.PayPanel;

public class DeviceDAO {

	static Connection conn;
	static PreparedStatement ps, ps2, ps3;
	static ResultSet rs;

	PosVO pos;

	String today;

	public DeviceDAO() {}



	// POS ----------------------------------------------------------------------------------------
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
//				conn.commit();
				System.err.println("TEST : deleteAmonthAgoDate() Ŀ�� ���� ����������");
				System.out.println("����1 : " + sql01);
				System.out.println("����2 : " + sql02);
				System.out.println(amonth_ago + "���� �Ǹ� ���� "
								+ check01 +"��� / ��������"+ check02 + "���� �����Ǿ����ϴ�.");
				return true;
			} else {
				conn.rollback();
				System.err.println("deleteAmonthAgoDate() ���� ���� ó���� ���� �ʾҽ��ϴ�.");

				return true;
//				return false;

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
				System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.saveDailyAmount()(sql01)");
			}


			String sql02 = "insert into daily_sales_amount values (?, ?, ?)";
			ps2 = conn.prepareStatement(sql02);

			ps2.setString(1, today);
			ps2.setInt(2, total_money);
			ps2.setInt(3, 1234); // device_id


			try {
				int result = ps2.executeUpdate();
				if(result != 1){
					conn.rollback();
					System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.saveDailyAmount() (sql02)");
				} else {
					//					conn.commit();
					System.err.println("TEST : saveDailyAmount() Ŀ�� ���� ����������");
					System.out.println("daily_sales_amount ���̺��� " + result + "���� ���� �Ǿ����ϴ�.");
					System.out.printf(">> SALES_DATE : %s\t TOTAL_MONEY : %d\t Device_id : %d\n", 
										today, total_money, 1234);
					System.out.println();
					return true;
				}

			} catch (SQLIntegrityConstraintViolationException sie){
				System.err.println("TEST : "+ today +"���� �����ʹ� �̹� �ֽ��ϴ�!");
				System.err.println("�������� ó���� ���� �ʾҽ��ϴ�.saveDailyAmount() (PK)");
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



	// ���� ��ȸ ���� ----------------------------------------------------------------------------
	/*
		cart TABLE ��� ���� ��ȸ
	 */
	public ArrayList<PosVO> searchAllCart() {

		ArrayList<PosVO> cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
					+ " from cart order by saled_date desc");
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
					+ " order by saled_date desc";
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
 	���� ���� ���� ��ȸ
	 */
	public ArrayList<PosVO> searchAllPayment() {

		ArrayList<PosVO> paylist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select * from payment order by Payment_date desc");

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

				paylist.add(pos);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return paylist;

	}

	/*
 	���� ���� �˻� ��ȸ
	 */
	public ArrayList<PosVO> searchPayment(String column_name, String column_data) {

		ArrayList<PosVO> paylist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			String sql = "";
			today = String.format("%s%%", new Day().TodayYmdD());

			if (column_name.equals("payment_date")) {
				sql = "select * from payment where " + column_name 
						+ " like \'%"+ today + column_data + ":%\' order by payment_date desc";
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

				paylist.add(pos);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return paylist;

	}


	public ArrayList<PosVO> findByAll(){
		conn = DBManager.getConnection();
		ArrayList<PosVO> members = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from customer");
			rs = ps.executeQuery();
			while(rs.next()) {
				pos = new PosVO();
				pos.setCustomer_no(rs.getString("customer_no"));
				pos.setM_first_name(rs.getString("first_name"));
				pos.setM_last_name(rs.getString("last_name"));
				pos.setM_contact_no(rs.getString("contact_no"));
				pos.setAmount_price(rs.getInt("Amount_price"));
				pos.setMembership(rs.getString("membership"));
				pos.setAccumulation_pct(rs.getDouble("accumulation_pct"));
				pos.setMileage(rs.getInt("mileage"));
				members.add(pos);
			}
			return members;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return null;
	}

	
	public ArrayList<PosVO> searchMember(String column_name, String column_data) {

		ArrayList<PosVO> members = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			String sql = "";
			if (column_name.equals("last_name||first_name") 
					|| column_name.equals("contact_no")) {
				sql = "select * from customer where " + column_name + " like \'%" + column_data + "%\'";
			} else {
				sql = "select * from customer where " + column_name + " = \'" + column_data + "\'";
			}
			System.out.println(column_data);
			System.out.println(sql);
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				pos = new PosVO();

				pos.setCustomer_no(rs.getString("customer_no"));
				pos.setM_first_name(rs.getString("first_name"));
				pos.setM_last_name(rs.getString("last_name"));
				pos.setM_contact_no(rs.getString("contact_no"));
				pos.setAmount_price(rs.getInt("Amount_price"));
				pos.setMembership(rs.getString("membership"));
				pos.setAccumulation_pct(rs.getDouble("accumulation_pct"));
				pos.setMileage(rs.getInt("mileage"));

				members.add(pos);
			}

			DBManager.r_p_c_Close(rs, ps, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return members;

	}


}
