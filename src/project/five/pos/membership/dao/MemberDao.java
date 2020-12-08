package project.five.pos.membership.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import project.five.pos.membership.models.Member;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao instance=new MemberDao();
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection conn; //DB ���� ��ü
	private PreparedStatement pstmt; //Query �ۼ� ��ü
	private ResultSet rs; //Query ��� Ŀ��
	
	//���� 1, ���� -1, ���� 0
	public int findByUsernameAndPassword(String username, String password) {
		//1. DB ����
		conn = DBConnection.getConnection();
		
		try {
			//2. Query �ۼ�
			pstmt = conn.prepareStatement("select * from member where username = ? and password = ?");
			
			//3. Query ? �ϼ� (index 1�� ���� ����)
			//setString, setInt, setDouble, setTimeStamp ���� ����.
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
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
			pstmt = conn.prepareStatement("insert into member values(member_seq.nextval, ?,?,?,?,?)");
			pstmt.setString(1, member.getUsername());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getPhone());
			pstmt.executeUpdate(); //return���� ó���� ���ڵ��� ����
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//���� Vector<Member>, ���� null
	public Vector<Member> findByAll(){
		conn = DBConnection.getConnection();
		Vector<Member> members = new Vector<>();
		try {
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("Birth"));
				member.setPhone(rs.getString("phone"));
				members.add(member);
			}
			return members;
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
