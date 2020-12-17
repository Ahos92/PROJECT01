package project.five.pos.membership.models;

import java.sql.SQLException;

import project.five.pos.membership.dao.DBConnection;

public class Member {
	private String first_name;
	private String last_name;
	private String contact_no;
	private int amount_price;
	private String membership;
	private double accumulation_pct;
	private int mileage;
	private String customer_no;
	private Object member;
	
	
	public Member() {
	
	}
	

	public Member( String customer_no, String first_name, String last_name, String contact_no,
			int amount_price, String membership, double accumulation_pct, int mileage) {	
		
		this.customer_no = customer_no;
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact_no = contact_no;
		this.amount_price = amount_price;
		this.membership = membership;
		this.accumulation_pct = accumulation_pct;
		this.mileage = mileage;
		
	}

	public String getCustomer_no() {
		
//		return contact_no.substring(contact_no.length()-4);
		return contact_no;
	}
	
	public void setCustomer_no(String contact_no) {
		this.customer_no = contact_no;
	}

	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
	public String getContact_no() {
		return contact_no;
	}
	
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	
	public int getAmount_price() {
		return amount_price;
	}
	
	public void setAmount_price(int amount_price) {
		this.amount_price = amount_price;
	}
	
	
	public String getMembership() {
		return membership;
	}
	
	public void setMembership(String membership) {
		this.membership = membership;
	}
	
	
	public double getAccumulation_pct() {
		return accumulation_pct;
	}
	
	public void setAccumulation_pct(double accumulation_pct) {
		this.accumulation_pct = accumulation_pct;
	}
	
	
	public int getMileage() {
		return mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	

}

