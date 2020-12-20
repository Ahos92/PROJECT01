package project.five.pos.membership.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import project.five.pos.db.DBManager;
import project.five.pos.db.PosVO;
import project.five.pos.membership.dao.MemberDao;
import project.five.pos.membership.models.Member;

public class MemberDao {
	
	public MemberDao() {
		
	}
	
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection conn; //DB ���� ��ü
	private PreparedStatement pstmt; //Query �ۼ� ��ü
	private ResultSet rs; //Query ��� Ŀ��
	
	//���� 1, ���� -1, ���� 0
	public int findByUsernameAndPassword(String customer_no) {
		//1. DB ����
		conn = DBManager.getConnection();
		
		try {
			//2. Query �ۼ�
			pstmt = conn.prepareStatement("select * from customer where customer_no = ?");
			
			//3. Query ? �ϼ� (index 1�� ���� ����)
			//setString, setInt, setDouble, setTimeStamp ���� ����.
			pstmt.setString(1, customer_no);
			
			//4. Query ����
			//(1) executeQuery() = select = ResultSet ����
			//(2) executeUpdate() = insert, update, delete = ���� ����.
			rs = pstmt.executeQuery();
			
			//5. rs�� query�� ����� ù��° ��(���ڵ�) ������ �����
			//����� count(*) �׷��Լ��̱� ������ 1���� ���� ���ϵ�. while���� �ʿ� ����.
			if(rs.next()) { //next()�Լ��� Ŀ���� ��ĭ �����鼭 �ش� �࿡ �����Ͱ� ������ true, ������ false ��ȯ
				//����� �ִٴ� ���� �ش� ���̵�� ����� ��Ī�Ǵ� ���� �ִٴ� ��.
				return 1; //�α��� ����
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return -1; //�α��� ����
	}
	
	//���� 1, ���� -1, 
	public int save(Member member) {
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement("insert into customer values(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, member.getCustomer_no());
			pstmt.setString(2, member.getLast_name());			
			pstmt.setString(3, member.getFirst_name());
			pstmt.setString(4, member.getContact_no());
			
			pstmt.setString(5, "bronze");	// ���	
			pstmt.setDouble(6, 0.01);	// ������
			pstmt.setInt(7, 0);	// member.getAmount_price()
			pstmt.setInt(8, 0);		// member.getMileage()
			


			
			pstmt.executeUpdate(); //return���� ó���� ���ڵ��� ����
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// ȸ�� ����
	public int delete(String customer_no) {
		conn = DBManager.getConnection();

		try {
			pstmt = conn.prepareStatement("delete from customer where customer_no = ?");
			pstmt.setString(1, customer_no);
			
			pstmt.executeUpdate(); 
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}