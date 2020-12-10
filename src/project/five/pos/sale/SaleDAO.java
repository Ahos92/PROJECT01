package project.five.pos.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;

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
	public ArrayList<SaleDTO> saveCartlist(ArrayList<SaleDTO> cartlist, int orderNumber) {

		try {
			conn = DBManager.getConnection();

			//conn.setAutoCommit(false);

			String sql = "insert into cart values(cart_seq.nextval, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < cartlist.size(); i++) {

				ps.setInt(1, orderNumber); // order_no
				ps.setInt(2, cartlist.get(i).getProduct_no()); // product_no
				ps.setInt(3, cartlist.get(i).getOrder_count()); // selected_count				
				// 어차피 한번 계산한 값 들고오기 (주문 가격)
				ps.setInt(4, cartlist.get(i).getProduct_price() * cartlist.get(i).getOrder_count());
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

	// 신규 주문번호 판별을 위함
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
	 	조건에 맞는 데이터
	 */
	public ArrayList<SaleDTO> searchCart(String column_name, String column_data) {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select cart_no, order_no, product_name, selected_item, total_price"
					+ " from cart inner join product using(product_no)"
					+ " where " + column_name + " = " + column_data
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
	
}
