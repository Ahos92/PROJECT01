package project.five.pos.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import project.five.pos.db.DBManager;
import project.five.pos.db.PosVO;
import project.five.pos.db.Day;

public class CartDAO{

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	PosVO cart;

	ArrayList<PosVO> cartlist;

	String today;

	public CartDAO() {}

	
	/*
	 * 	마지막 결제 창에서 넘겨 받을 데이터 집어넣기
	 */
	public void saveCartlist() {
		
		conn = DBManager.getConnection();
		
		try {
			String sql = "insert into cart "
								+ "values(cart_seq.nextval, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			
			// cart_no : seq.nextval
//			ps.setInt(1, x); // order_no
//			ps.setInt(2, x); // product_no
//			ps.setInt(3, x); // selected_item
//			ps.setTimestamp(4, x); // saled_date
//			ps.setInt(5, x); // total_price
//			ps.setInt(6, x); // device_id

			rs = ps.executeQuery();


			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	/*
	 	최신 주문번호
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

			System.out.println("현재 주문 번호 : " + max);

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return max;
	}


	/*
	 	cart TABLE 모든 정보를 담은 객체 반환 
	 		- 조회하고 싶은 컬럼 추가, 삭제 할수 있음
	 */
	public ArrayList<PosVO> searchAllCart() {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			today = new Day().TodayYmd();
			System.out.println(today);
			ps = conn.prepareStatement("select *"
					+ " from cart inner join product using(product_no)"
					+ " where saled_date like \'%" + today
					+ "%\' order by cart_no asc");
			rs = ps.executeQuery();

			while (rs.next()) {
				cart = new PosVO();

				cart.setCart_no(rs.getInt("cart_no"));
				cart.setOrder_no(rs.getInt("order_no"));
				cart.setProduct_name(rs.getString("product_name"));
				cart.setSelected_item(rs.getInt("selected_item"));
				cart.setTotal_price(rs.getInt("total_price"));

				cartlist.add(cart);
			}

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartlist;

	}


	/*
	 	조건에 맞는 cart TABLE 정순 데이터
	 		- 정렬 기준 컬럼 이름, 값
	 		- 조건에 맞는 상품 정보만 담은 객체 반환 
	 */
	public ArrayList<PosVO> searchCart(String column_name, String column_data) {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			if (column_name.equals("product_name")) {
				column_data = "\'" + column_data + "\'";
			} 

			ps = conn.prepareStatement("select *"
					+ " from cart inner join product using(product_no)"
					+ " where " + column_name + " = " + column_data
					+ " order by cart_no asc");
			try {
				rs = ps.executeQuery();
			} catch (SQLSyntaxErrorException sse) {
				System.err.println("잘못된 입력");
			}
			while (rs.next()) {
				cart = new PosVO();

				cart.setCart_no(rs.getInt("cart_no"));
				cart.setOrder_no(rs.getInt("order_no"));
				cart.setProduct_name(rs.getString("product_name"));
				cart.setTermsofcondition(rs.getString("termsofcondition"));
				cart.setSelected_item(rs.getInt("selected_item"));
				cart.setTotal_price(rs.getInt("total_price"));

				cartlist.add(cart);
			}

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			System.err.println("잘못된 입력");
		}
		return cartlist;

	}

	
	/*
	 * 	하루치 판매금액
	 */	
	public int SumByToday() {
		int sum = 0;
		conn = DBManager.getConnection();

		try {
			today = new Day().TodayYmd();
			String sql = "select sum(total_price) from cart where saled_date like \'%" + today + "%\'";
			ps = conn.prepareStatement(sql);

			System.out.println("총 매출 쿼리 : " + sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				sum = rs.getInt("sum(total_price)");
				System.out.println(today + "날의 총 매출 : " + sum);
			}

		} catch (SQLException e) {

		}

		return sum;
	}


		/* 
		- 결제 화면에 넘겨줄 데이터 저장
		- 실시간 업데이트
	 */
	public ArrayList<PosVO> saveUpdateCartlist(ArrayList<PosVO> cartlist, int orderNumber, String device_id) {
	
		try {
			conn = DBManager.getConnection();
			
//			conn.setAutoCommit(false);
			
			String sql = "insert into cart "
						+ "values(cart_seq.nextval, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
	
			java.sql.Timestamp now = java.sql.Timestamp.valueOf(LocalDateTime.now());
	
			for (int i = 0; i < cartlist.size(); i++) {
				
				ps.setInt(1, orderNumber); // order_no
				ps.setInt(2, cartlist.get(i).getProduct_no()); // product_no
				ps.setInt(3, cartlist.get(i).getSelected_item()); // selected_count				
				// 어차피 한번 계산한 값 들고오기 (주문 가격)
				ps.setTimestamp(4, now); // saled_date
				ps.setInt(5, cartlist.get(i).getTotal_price()); // total_price
				ps.setInt(6, Integer.parseInt(device_id)); // device_id
				
				ps.addBatch();
	
			}
			
			int[] rows = ps.executeBatch();
			if (rows.length == 0) {
				System.err.println("결제 품목이 없습니다.");
			} else {
				System.out.println(rows.length + "행이 변경 되었습니다.");
			}
			DBManager.p_c_Close(ps, conn);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return cartlist;
	}	
	//
	//	
	//	/*
	//	  	선택된 상품의 총 가격 
	//	  		- 주문 번호로 판별
	//	 */
	//	public int SumByOrderNum(int orderNumber) {
	//		int sum = 0;
	//		conn = DBManager.getConnection();
	//		
	//		try {
	//			
	//			String sql = "select sum(total_price) from cart where order_no = ?";
	//			ps = conn.prepareStatement(sql);
	//			
	//			ps.setInt(1, orderNumber);
	//			
	//			rs = ps.executeQuery();
	//			
	//			if (rs.next()) {
	//				sum = rs.getInt("sum(total_price)");
	//			}
	//		} catch (SQLException e) {
	//
	//		}
	//		
	//		return sum;
	//	}
	//	
	//	
	//	/*
	//	  	몇 종류의 상품을 선택 했는지에 대한 메서드
	//	  		- 주문번호로 판별
	//	 */
	//	public int CountOrderNum(int orderNumber) {
	//		int cnt = 0;
	//		conn = DBManager.getConnection();
	//		
	//		try {
	//			String sql = "select count(cart_no) from cart where order_no = ?";
	//			ps = conn.prepareStatement(sql);
	//			
	//			ps.setInt(1, orderNumber);
	//			
	//			rs = ps.executeQuery();
	//			
	//			if (rs.next()) {
	//				cnt = rs.getInt("count(cart_no)");
	//			}
	//		} catch (SQLException e) {
	//
	//		}
	//		
	//		return cnt;
	//	}

	public static void main(String[] args) {
		CartDAO dao = new CartDAO();
		System.out.println(dao.SumByToday());
	}
}
