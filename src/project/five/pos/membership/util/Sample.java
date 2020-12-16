package project.five.pos.membership.util;

import java.util.Vector;
import project.five.pos.membership.models.Member;

public class Sample {
	
	public static Vector<String> getMemberName(){
		//칼럼 데이터
		Vector<String> memberName = new Vector<>();
		memberName.add("아이디");
		memberName.add("성");
		memberName.add("이름");
		memberName.add("전화번호");
		memberName.add("누적금액");
		memberName.add("등급");
		memberName.add("적립률");
		memberName.add("마일리지");
		
		return memberName;
	}
	
	public static Vector<Member> getMembers(){
		//Member 데이터
		Vector<Member> members = new Vector<>();
		members.add(new Member(null, "Hong", "Gil Dong", "010-3333-3333", 10000, "bronze", 0.01, 100));	// , 10000, "bronze", 0.01, 100
		return members;
	}
}
