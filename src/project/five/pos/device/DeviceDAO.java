package project.five.pos.device;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import project.five.pos.db.DBManager;
import project.five.pos.db.PosVO;

public class DeviceDAO {

	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	PosVO posVo;

	public DeviceDAO() {

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

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
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

			DBManager.p_r_c_Close(ps, rs, conn);

		} catch (SQLException e) {
			System.err.println("������ �α��� ����!");
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
			ps.setString(3, business.getLast_name()); 	// last_name VARCHAR2(20)
			ps.setString(4, business.getFirst_name());	// first_name VARCHAR2(20)
			ps.setString(5, business.getContact_no());	// contact_no VARCHAR2(30)
			
			try {
				ps.addBatch();
				
			} catch (BatchUpdateException e) {
				// ���̾�α� �ϳ�����
				System.err.println("���̵� �̹� �ֽ��ϴ�.");
			}
			
			ps.executeBatch();
			
			System.out.println("ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
	
//	public static void main(String[] args) {
//		PosVO business = new PosVO(); 
//		business.setBusiness_id(123);
//		business.setBusiness_pw("45");
//		business.setLast_name("��");
//		business.setFirst_name("��ȣ");
//		business.setContact_no("010-0000-0000");
//		new DeviceDAO().SighUPManager(business);
//	}
}
