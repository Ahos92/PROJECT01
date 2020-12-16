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
		conn = DBConnection.getConnection();
		
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
		conn = DBConnection.getConnection();
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
		conn = DBConnection.getConnection();

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
	
	//���� Vector<Member>, ���� null
	public ArrayList<PosVO> findByAll(){
		conn = DBConnection.getConnection();
		ArrayList<PosVO> members = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select * from customer");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PosVO member = new PosVO();
				member.setCustomer_no(rs.getString("customer_no"));
				member.setM_first_name(rs.getString("first_name"));
				member.setM_last_name(rs.getString("last_name"));
				member.setM_contact_no(rs.getString("contact_no"));
				member.setAmount_price(rs.getInt("Amount_price"));
				member.setMembership(rs.getString("membership"));
				member.setAccumulation_pct(rs.getDouble("accumulation_pct"));
				member.setMileage(rs.getInt("mileage"));
				members.add(member);
			}
			return members;
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<PosVO> searchMember(String column_name, String column_data) {

		ArrayList<PosVO> members = new ArrayList<>();

		conn = DBManager.getConnection();

		try {
			pstmt = conn.prepareStatement("select * "
										+ "from customer "
										+ "where " + column_name + " = "+ column_data);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PosVO member = new PosVO();
				
				member.setCustomer_no(rs.getString("customer_no"));
				member.setM_first_name(rs.getString("first_name"));
				member.setM_last_name(rs.getString("last_name"));
				member.setM_contact_no(rs.getString("contact_no"));
				member.setAmount_price(rs.getInt("Amount_price"));
				member.setMembership(rs.getString("membership"));
				member.setAccumulation_pct(rs.getDouble("accumulation_pct"));
				member.setMileage(rs.getInt("mileage"));
				
				members.add(member);
			}
		
			DBManager.p_r_c_Close(pstmt, rs, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return members;

	}
}