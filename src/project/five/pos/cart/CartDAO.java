package project.five.pos.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import project.five.pos.db.DBManager;
import project.five.pos.db.Day;
import project.five.pos.db.PosVO;

public class CartDAO {

	static Connection conn;
	static PreparedStatement ps, ps2, ps3;
	static ResultSet rs;

	PosVO pos;

	String today;
	
	public CartDAO() {}
	
	/*
	 * 	마지막 결제 창에서 넘겨 받을 데이터 집어넣기
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
				
				ps.setString(1, today + division); 					// saled_date  - 날짜받아서 변환
				ps.setInt(2, order_no); 							// order_no    - 매개변수 order_no
				ps.setString(3, name_list.get(j)); 					// sale_product_name - 이름객체받아서 그대로 입력
				ps.setInt(4, cart_list.get(j).getSelected_item()); // selected_item	- 매개변수 date
				ps.setInt(5, cart_list.get(j).getTotal_price()); 	// total_price	- 객체받아서 가격
				ps.setInt(6, device_id); 							// device_id	- 매개변수 device_id
			
				ps.addBatch();
			}
			
			System.out.println(name_list.get(0));
			System.out.println(cart_list.get(0).getSelected_item());
			System.out.println(cart_list.get(0).getTotal_price());
			
			int[] rows = ps.executeBatch();
			if (rows.length <= 0) {
				return false;
			} else {
				System.out.println("cart TABLE의 " + rows.length + "행이 변경되었습니다.");
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.r_p_c_Close(rs, ps, conn);
			} catch (SQLException e) {}

		}

		return max;
	}

}
