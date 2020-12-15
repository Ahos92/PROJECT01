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

public class SaleDAO {

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	SaleDTO saleDTO;

	ArrayList<SaleDTO> cartlist;

	public SaleDAO() {

	}

	/*
	   테스트 메서드
	   	상품이 넘어오는거 가정
	 */
	public SaleDTO testOrder(String name, String opt, int count) {
		// 상품 데이터 담을 객체
		saleDTO = new SaleDTO();

		conn = DBManager.getConnection();

		try {

			// 옵션 값이 없을때
			if (opt == null) {
				ps = conn.prepareStatement("select *"
						+ " from product"
						+ " where product_name = ?"
						);
				ps.setString(1, name);

			} else {
				ps = conn.prepareStatement("select *"
						+ " from product"
						+ " where product_name = ?"
						+ " and termsofcondition = ?"
						);

				ps.setString(1, name);
				ps.setString(2, opt);
			}
			rs = ps.executeQuery();				

			while (rs.next()) {

				String pName = rs.getString("product_name");
				String option = rs.getString("termsofcondition"); 

				saleDTO.setProduct_no(rs.getInt("product_no"));
				saleDTO.setProduct_name(pName);
				saleDTO.setTermsofcondition(option);
				saleDTO.setProduct_price(rs.getInt("product_price"));
				saleDTO.setOrder_count(count);

				System.out.printf("%s(%s) %d개 주문\n", pName, option, count);
			}

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return saleDTO;
	}


	/* 
	   장바구니 내역 cart TABLE에 저장
		정산 관리 위한 데이터 저장
		모드 2개로나눠서 
			- 오토커밋 해제 모드(처음 주문결제 넘어갈 때) 
			- 적용 모드(최종 결제 완료) 
			할 예정(미정)
	 */
	public ArrayList<SaleDTO> saveCartlist(ArrayList<SaleDTO> cartlist, int orderNumber, String device_id) {

		try {
			conn = DBManager.getConnection();

			//conn.setAutoCommit(false);

			String sql = "insert into cart "
						+ "values(cart_seq.nextval, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);

			java.sql.Timestamp now = java.sql.Timestamp.valueOf(LocalDateTime.now());

			for (int i = 0; i < cartlist.size(); i++) {
				
				ps.setInt(1, orderNumber); // order_no
				ps.setInt(2, cartlist.get(i).getProduct_no()); // product_no
				ps.setInt(3, cartlist.get(i).getOrder_count()); // selected_count				
				// 어차피 한번 계산한 값 들고오기 (주문 가격)
				ps.setInt(4, cartlist.get(i).getProduct_price()
						* cartlist.get(i).getOrder_count()); // total_price
				ps.setInt(5, Integer.parseInt(device_id)); // device_id
				ps.setTimestamp(6, now); // saled_date
				
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

	
	/*
	 	최신 주문번호 판별
	*/ 
	public int MaxOrderNumber() {

		int max = 0;

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select order_no from cart order by order_no asc");

			rs = ps.executeQuery();

			while (rs.next()) {
				max = rs.getInt("order_no");
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
	public ArrayList<SaleDTO> searchAllCart() {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
									+ " from cart inner join product using(product_no)"
									+ " order by cart_no asc");

			rs = ps.executeQuery();

			while (rs.next()) {
				saleDTO = new SaleDTO();
				
				saleDTO.setCart_no(rs.getInt("cart_no"));
				saleDTO.setOrder_no(rs.getInt("order_no"));
				saleDTO.setProduct_name(rs.getString("product_name"));
				saleDTO.setSelected_item(rs.getInt("selected_item"));
				saleDTO.setTotal_price(rs.getInt("total_price"));
				
				cartlist.add(saleDTO);
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
	public ArrayList<SaleDTO> searchCart(String column_name, String column_data) {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
					+ " from cart inner join product using(product_no)"
					+ " where " + column_name + " = " + column_data
					+ " order by cart_no asc");

			rs = ps.executeQuery();

			while (rs.next()) {
				saleDTO = new SaleDTO();
				
				saleDTO.setCart_no(rs.getInt("cart_no"));
				saleDTO.setOrder_no(rs.getInt("order_no"));
				saleDTO.setProduct_name(rs.getString("product_name"));
				saleDTO.setTermsofcondition(rs.getString("termsofcondition"));
				saleDTO.setSelected_item(rs.getInt("selected_item"));
				saleDTO.setTotal_price(rs.getInt("total_price"));
				
				cartlist.add(saleDTO);
			}
		
			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartlist;

	}

	
	/*
	  	존재하는 포스기계 인지 판별, 기계 로그인 할 때 사용
	 */
	public boolean searchPOS(int device_id, String device_pw) {
		
		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select * from pos where device_id = ? and device_id = ?");

			ps.setInt(1, device_id);
			ps.setString(2, device_pw);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("로그인 성공");
				return true;
			} 
			
			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.err.println("로그인 실패");
		return false;
		
	}
	
	
	/*
	  	판매될 상품의 총 가격 
	  		- 주문 번호로 판별
	 */
	public int SumByOrderNum(int orderNumber) {
		int sum = 0;
		conn = DBManager.getConnection();
		
		try {
			
			String sql = "select sum(total_price) from cart where order_no = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, orderNumber);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				sum = rs.getInt("sum(total_price)");
			}
		} catch (SQLException e) {

		}
		
		return sum;
	}
	
	
	/*
	  	몇 종류의 상품을 선택 했는지에 대한 메서드
	  		- 주문번호로 판별
	 */
	public int CountOrderNum(int orderNumber) {
		int cnt = 0;
		conn = DBManager.getConnection();
		
		try {
			
			String sql = "select count(cart_no) from cart where order_no = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, orderNumber);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				cnt = rs.getInt("count(cart_no)");
			}
		} catch (SQLException e) {

		}
		
		return cnt;
	}
	
	
	
	public static void main(String[] args) {
		SaleDAO dao = new SaleDAO();
		System.out.println(dao.CountOrderNum(2));
	}
}
