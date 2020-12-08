package project.five.pos.membership.util;

import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import project.five.pos.membership.models.Member;

public class Sample {
	
	public static Vector<String> getMemberName(){
		//칼럼 데이터
		Vector<String> memberName = new Vector<>();
		memberName.add("ID");
		memberName.add("USERNAME");
		memberName.add("PASSWORD");
		memberName.add("NAME");
		memberName.add("EMAIL");
		memberName.add("PHONE");
		memberName.add("CREATEDATE");
		
		return memberName;
	}
	
	public static Vector<Member> getMembers(){
		//Member 데이터
		Vector<Member> members = new Vector<>();
		members.add(new Member(1,"ssarmango","bitc5600","jooho","ssar@nate.com", "01022228888"));
		members.add(new Member(2,"ssarmango","bitc5600","jooho","ssar@nate.com", "01022228888"));
		members.add(new Member(3,"ssarmango","bitc5600","jooho","ssar@nate.com", "01022228888"));
		members.add(new Member(4,"ssarmango","bitc5600","jooho","ssar@nate.com", "01022228888"));
		members.add(new Member(5,"ssarmango","bitc5600","jooho","ssar@nate.com", "01022228888"));
		
		return members;
	}
}
