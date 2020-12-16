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
		conn = DBConnection.getConnection();
		
		try {
			//2. Query 작성
			pstmt = conn.prepareStatement("select * from customer where customer_no = ?");
			
			//3. Query ? 완성 (index 1번 부터 시작)
			//setString, setInt, setDouble, setTimeStamp 등이 있음.
			pstmt.setString(1, customer_no);
			
			//4. Query 실행
			//(1) executeQuery() = select = ResultSet 리턴
			//(2) executeUpdate() = insert, update, delete = 리턴 없음.
			rs = pstmt.executeQuery();
			
			//5. rs는 query한 결과의 첫번째 행(레코드) 직전에 대기중
			//결과가 count(*) 그룹함수이기 때문에 1개의 행이 리턴됨. while문이 필요 없음.
			if(rs.next()) { //next()함수는 커서를 한칸 내리면서 해당 행에 데이터가 있으면 true, 없으면 false 반환
				//결과가 있다는 것은 해당 아이디와 비번에 매칭되는 값이 있다는 뜻.
				return 1; //로그인 성공
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return -1; //로그인 실패
	}
	
	//성공 1, 실패 -1, 
	public int save(Member member) {
		conn = DBConnection.getConnection();
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
			e.printStackTrace();
		}
		return -1;
	}
	
	// 회원 삭제
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
	
	//성공 Vector<Member>, 실패 null
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