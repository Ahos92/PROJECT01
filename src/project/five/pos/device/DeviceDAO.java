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
 	한달전 데이터 지우는 메서드
 		- boolean으로 정산 처리 결과를 받아 에러 나면 프로그램 종료 X

 		- test : true로 고정
	 */
	public boolean deleteAmonthAgoDate() {

		conn = DBManager.getConnection();

		try {

			// 테스트 끝나면 지우기
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
				System.err.println("TEST : deleteAmonthAgoDate() 커밋 설정 해제되있음");
				System.out.println("쿼리1 : " + sql01);
				System.out.println("쿼리2 : " + sql02);
				System.out.println(amonth_ago + "날의 판매 내역 "
								+ check01 +"행과 / 결제내역"+ check02 + "행이 삭제되었습니다.");
				return true;
			} else {
				conn.rollback();
				System.err.println("deleteAmonthAgoDate() 정상 적인 처리가 되지 않았습니다.");

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

		System.err.println("정상 적인 처리가 되지 않았습니다. deleteAmonthAgoDate() 본사에 연락해주세요. 02-000-0000");
		//return false;
		return true;
	}

	/*
	하루 매출 저장 메서드
		- boolean으로 정산 처리 결과를 받아 에러 나면 프로그램 종료 X		
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

				System.out.printf("%s날의 총 매출 : %d\n", today, total_money);


			} else {
				conn.rollback();
				System.err.println("정상적인 처리가 되지 않았습니다.saveDailyAmount()(sql01)");
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
					System.err.println("정상적인 처리가 되지 않았습니다.saveDailyAmount() (sql02)");
				} else {
					//					conn.commit();
					System.err.println("TEST : saveDailyAmount() 커밋 설정 해제되있음");
					System.out.println("daily_sales_amount 테이블의 " + result + "행이 변경 되었습니다.");
					System.out.printf(">> SALES_DATE : %s\t TOTAL_MONEY : %d\t Device_id : %d\n", 
										today, total_money, 1234);
					System.out.println();
					return true;
				}

			} catch (SQLIntegrityConstraintViolationException sie){
				System.err.println("TEST : "+ today +"날의 데이터는 이미 있습니다!");
				System.err.println("정상적인 처리가 되지 않았습니다.saveDailyAmount() (PK)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				DBManager.p2_r_p_c_Close(ps2, rs, ps, conn);

			} catch (SQLException e) {}
		}

		System.err.println("정상적인 처리가 되지 않았습니다. saveDailyAmount() 본사에 연락해주세요. 02-000-0000");
		return false;
	}

	/*
	존재하는 포스기계 인지 판별, 기계 로그인 할 때 사용	  
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
				System.out.println("DEVICE 로그인 성공!");
				return true;
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				DBManager.r_p_c_Close(rs, ps, conn);

			} catch (SQLException e) {}
		}

		System.err.println("DEVICE 로그인 실패");
		return false;
	}


	/*
	존재하는 관리자 아이디 인지 판별, 기계 로그인 할 때 사용  	
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
				System.out.println("관리자 로그인 성공!");
				return true;
			} 

		} catch (SQLException e) {
			System.err.println("관리자 로그인 실패!");
		} finally {

			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		System.err.println("관리자 로그인 실패!");
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
				System.err.println("아이디가 이미 있습니다.");
				return false;
			}

			try {
				ps.executeBatch();

			} catch (BatchUpdateException e) {
				System.err.println("모든 항목을 정확하게 입력해주세요!");
				e.printStackTrace();
				return false;
			}

			System.out.println("회원 가입이 완료되었습니다.");
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



	// 각종 조회 관련 ----------------------------------------------------------------------------
	/*
		cart TABLE 모든 정보 조회
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
		조건에 맞는 cart TABLE 정순 데이터
			- 정렬 기준 컬럼 이름, 값
			- 조건에 맞는 상품 정보만 담은 객체 반환 
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
				System.err.println("잘못된 입력");
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
			System.err.println("잘못된 입력");
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}
		}

		return cartlist;

	}

	/*
 	결제 내역 전부 조회
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
 	결제 내역 검색 조회
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
