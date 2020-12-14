package project.five.pos.manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import project.five.pos.db.DBManager;

public class DeleteMenu{

	private Connection con;
	private PreparedStatement pstmt;
	
	public DeleteMenu(String pno) {
		String sql = "DELETE FROM PRODUCT WHERE product_no=?";
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, pno);
			int cnt = pstmt.executeUpdate();
			
			System.out.printf("%d행이 삭제되었습니다",cnt);
			
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {}
		}
	}

}
