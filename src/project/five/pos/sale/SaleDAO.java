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

public class SaleDAO{

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	PosVO posVo;

	ArrayList<PosVO> cartlist;

	public SaleDAO() {

	}

	/*
	   �׽�Ʈ �޼���
	   	��ǰ�� �Ѿ���°� ����
	 */
	public PosVO testOrder(String name, String opt, int count) {
		// ��ǰ ������ ���� ��ü
		posVo = new PosVO();

		conn = DBManager.getConnection();

		try {

			// �ɼ� ���� ������
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

				posVo.setProduct_no(rs.getInt("product_no"));
				posVo.setProduct_name(pName);
				posVo.setTermsofcondition(option);
				posVo.setProduct_price(rs.getInt("product_price"));
				posVo.setOrder_count(count);

				System.out.printf("%s(%s) %d�� �ֹ�\n", pName, option, count);
			}

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return posVo;
	}


	/* 
	   ��ٱ��� ���� cart TABLE�� ����
		���� ���� ���� ������ ����
		��� 2���γ����� 
			- ����Ŀ�� ���� ���(ó�� �ֹ����� �Ѿ ��) 
			- ���� ���(���� ���� �Ϸ�) 
			�� ����(����)
	 */
	public ArrayList<PosVO> saveCartlist(ArrayList<PosVO> cartlist, int orderNumber, String device_id) {

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
				// ������ �ѹ� ����� �� ������ (�ֹ� ����)
				ps.setInt(4, cartlist.get(i).getProduct_price()
						* cartlist.get(i).getOrder_count()); // total_price
				ps.setInt(5, Integer.parseInt(device_id)); // device_id
				ps.setTimestamp(6, now); // saled_date
				
				ps.addBatch();

			}

			int[] rows = ps.executeBatch();
			if (rows.length == 0) {
				System.err.println("���� ǰ���� �����ϴ�.");
			} else {
				System.out.println(rows.length + "���� ���� �Ǿ����ϴ�.");
			}
			DBManager.p_c_Close(ps, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartlist;
	}	

	
	/*
	 	�ֽ� �ֹ���ȣ �Ǻ�
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

			System.out.println("���� �ֹ� ��ȣ : " + max);

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return max;
	}

	
	/*
	 	cart TABLE ��� ������ ���� ��ü ��ȯ 
	 		- ��ȸ�ϰ� ���� �÷� �߰�, ���� �Ҽ� ����
	 */
	public ArrayList<PosVO> searchAllCart() {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
									+ " from cart inner join product using(product_no)"
									+ " order by cart_no asc");

			rs = ps.executeQuery();

			while (rs.next()) {
				posVo = new PosVO();
				
				posVo.setCart_no(rs.getInt("cart_no"));
				posVo.setOrder_no(rs.getInt("order_no"));
				posVo.setProduct_name(rs.getString("product_name"));
				posVo.setSelected_item(rs.getInt("selected_item"));
				posVo.setTotal_price(rs.getInt("total_price"));
				
				cartlist.add(posVo);
			}
		
			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartlist;

	}

	
	/*
	 	���ǿ� �´� cart TABLE ���� ������
	 		- ���� ���� �÷� �̸�, ��
	 		- ���ǿ� �´� ��ǰ ������ ���� ��ü ��ȯ 
	 */
	public ArrayList<PosVO> searchCart(String column_name, String column_data) {

		cartlist = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			ps = conn.prepareStatement("select *"
					+ " from cart inner join product using(product_no)"
					+ " where " + column_name + " = " + column_data
					+ " order by cart_no asc");

			rs = ps.executeQuery();

			while (rs.next()) {
				posVo = new PosVO();
				
				posVo.setCart_no(rs.getInt("cart_no"));
				posVo.setOrder_no(rs.getInt("order_no"));
				posVo.setProduct_name(rs.getString("product_name"));
				posVo.setTermsofcondition(rs.getString("termsofcondition"));
				posVo.setSelected_item(rs.getInt("selected_item"));
				posVo.setTotal_price(rs.getInt("total_price"));
				
				cartlist.add(posVo);
			}
		
			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartlist;

	}

	
	/*
	  	�Ǹŵ� ��ǰ�� �� ���� 
	  		- �ֹ� ��ȣ�� �Ǻ�
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
	  	�� ������ ��ǰ�� ���� �ߴ����� ���� �޼���
	  		- �ֹ���ȣ�� �Ǻ�
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
		System.out.println(dao.MaxOrderNumber());
	}
}
