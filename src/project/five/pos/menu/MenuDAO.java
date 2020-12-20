package project.five.pos.menu;

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

public class MenuDAO {
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	PosVO posVo;
	
	static private String[] pnames;
	static private Integer[] pprice;
	static private String[] category;
	
	public MenuDAO() {
		
	}
	
	public static String[] getCategories(){
		String sql = "select product_category from product group by product_category";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<String> categoryList = new ArrayList<>();
			
			while(rs.next()) {
				categoryList.add(rs.getString("product_category"));
			}
			
			category = categoryList.toArray(new String[categoryList.size()]);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public static Object[][] getMenus(String cate){
		String sql = "select product_name, product_price,"
				+ "product_count, termsofcondition from product where product_category=?";
		Object[][] menus=null;
		int count=0;
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cate);
			rs = ps.executeQuery();
			while(rs.next()) {
				count++;
			}
			System.out.println(count);
			menus = new Object[count][4];
			
			rs = ps.executeQuery();
			int i = 0;
			while(rs.next()) {
				menus[i][0] = rs.getString("product_name");
				menus[i][1] = rs.getInt("product_price");
				menus[i][2] = rs.getInt("product_count");
				menus[i][3] = rs.getString("termsofcondition");
				i+=1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
	
	
}
