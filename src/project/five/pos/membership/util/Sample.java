package project.five.pos.membership.util;

import java.util.Vector;
import project.five.pos.membership.models.Member;

public class Sample {
	
	public static Vector<String> getMemberName(){
		//Į�� ������
		Vector<String> memberName = new Vector<>();
		memberName.add("���̵�");
		memberName.add("��");
		memberName.add("�̸�");
		memberName.add("��ȭ��ȣ");
		memberName.add("�����ݾ�");
		memberName.add("���");
		memberName.add("������");
		memberName.add("���ϸ���");
		
		return memberName;
	}
	
	public static Vector<Member> getMembers(){
		//Member ������
		Vector<Member> members = new Vector<>();
		members.add(new Member(null, "Hong", "Gil Dong", "010-3333-3333", 10000, "bronze", 0.01, 100));	// , 10000, "bronze", 0.01, 100
		return members;
	}
}
