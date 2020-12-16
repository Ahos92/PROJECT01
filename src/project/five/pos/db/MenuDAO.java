package project.five.pos.db;

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
	
	public MenuDAO() {
		
	}
	
	@SuppressWarnings("null")
	public static String[] getPnames(){
		String sql = "select product_name from product";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<String> pnameList = new ArrayList<>();
			
			while(rs.next()) {
				pnameList.add(rs.getString("product_name"));
			}
			
			pnames = pnameList.toArray(new String[pnameList.size()]);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pnames;
	}
	
	public static Integer[] getPprices(){
		String sql = "select product_price from product";
		try {
			conn = DBManager.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ArrayList<Integer> ppriceList = new ArrayList<Integer>();
			
			while(rs.next()) {
				ppriceList.add(rs.getInt("product_price"));
			}
			
			pprice = ppriceList.toArray(new Integer[ppriceList.size()]);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pprice;
	}

}
