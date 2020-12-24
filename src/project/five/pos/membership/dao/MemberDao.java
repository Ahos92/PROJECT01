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
	
	private Connection conn; //DB 연결 객체
	private PreparedStatement pstmt; //Query 작성 객체
	private ResultSet rs; //Query 결과 커서
	
	//성공 1, 실패 -1, 없음 0
	public int findByUsernameAndPassword(String customer_no) {
		//1. DB 연결
		conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement("select * from customer where customer_no = ?");
		
			pstmt.setString(1, customer_no);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				return 1; 
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return -1; 
	}
	
	//성공 1, 실패 -1, 
	public int save(Member member) {
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement("insert into customer values(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, member.getCustomer_no());
			pstmt.setString(2, member.getLast_name());			
			pstmt.setString(3, member.getFirst_name());
			pstmt.setString(4, member.getContact_no());		
			pstmt.setString(5, "bronze");	// 등급	
			pstmt.setDouble(6, 0.01);	// 적립률
			pstmt.setInt(7, 0);	// member.getAmount_price()
			pstmt.setInt(8, 0);		// member.getMileage()
	
			pstmt.executeUpdate(); //return값은 처리된 레코드의 개수
			return 1;
		} catch (Exception e) {
			System.err.println("잘못된 입력 입니다.");
		}
		return -1;
	}
	
	// 회원 삭제
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