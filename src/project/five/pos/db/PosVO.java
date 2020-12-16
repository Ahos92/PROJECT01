package project.five.pos.db;

import java.util.regex.Pattern;

public class PosVO {

	// pos table
	private int device_id; // cart fk
	private String device_pw;

	// branch table
	private int branch_no;// pos fk
	// business_id
	private String branch_name;
	private String branch_location;
	private String phone_number;

	// businessAdminister table
	private int business_id;
	private String business_pw;
	private String b_last_name;
	private String b_first_name;
	private String b_contact_no;

	// cart Table
	private int cart_no;
	private int order_no;
	private int selected_item;
	private int total_price;
	private String saled_date;

	// product table
	private int product_no;
	private String product_name;
	private int product_price;
	private int product_count;
	private String product_category;
	private String termsofcondition;
	
	// customer table
	private String customer_no;
	private String m_first_name;
	private String m_last_name;
	private String m_contact_no;
	private int amount_price;
	private String membership;
	private double accumulation_pct;
	private int mileage;

	// payment table
	private int payment_no;
	private String payment_type;
	private String payment_date;
	private String bank_id;
	private String card_num;
	private int amount_of_money;
	private int actual_expenditure;
	private int coupon_no;
	
	
	public int getActual_expenditure() {
		return actual_expenditure;
	}

	public void setActual_expenditure(int actual_expenditure) {
		this.actual_expenditure = actual_expenditure;
	}

	public int getPayment_no() {
		return payment_no;
	}

	public void setPayment_no(int payment_no) {
		this.payment_no = payment_no;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getBank_id() {
		return bank_id;
	}

	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public int getAmount_of_money() {
		return amount_of_money;
	}

	public void setAmount_of_money(int amount_of_money) {
		this.amount_of_money = amount_of_money;
	}

	public int getCoupon_no() {
		return coupon_no;
	}

	public void setCoupon_no(int coupon_no) {
		this.coupon_no = coupon_no;
	}

	public String getSaled_date() {
		return saled_date;
	}

	public void setSaled_date(String saled_date) {
		this.saled_date = saled_date;
	}

	public String getM_first_name() {
		return m_first_name;
	}

	public void setM_first_name(String m_first_name) {
		this.m_first_name = m_first_name;
	}

	public String getM_last_name() {
		return m_last_name;
	}

	public void setM_last_name(String m_last_name) {
		this.m_last_name = m_last_name;
	}

	public String getM_contact_no() {
		return m_contact_no;
	}

	public void setM_contact_no(String m_contact_no) {
		this.m_contact_no = m_contact_no;
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

	public String getCustomer_no() {
		return m_contact_no.substring(m_contact_no.length()-4);
	}

	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}

	public String getBusiness_pw() {
		return business_pw;
	}

	public void setBusiness_pw(String business_pw) {
		this.business_pw = business_pw;
	}

	public String getB_last_name() {
		return b_last_name;
	}

	public void setB_last_name(String b_last_name) {
		String name_regex = "^[°¡-ÆR]*$";
		if (Pattern.matches(name_regex, b_last_name)) {
			this.b_last_name = b_last_name;
		} else {
			this.b_last_name = null;
		}
	}

	public String getB_first_name() {
		return b_first_name;
	}

	public void setB_first_name(String b_first_name) {
		String name_regex = "^[°¡-ÆR]*$";
		if (Pattern.matches(name_regex, b_first_name)) {
			this.b_first_name = b_first_name;
		} else {
			this.b_first_name = null;
		}
	}

	public String getB_contact_no() {
		return b_contact_no;
	}

	public void setB_contact_no(String b_contact_no) {
		String phoneNumber_regex = "01[016789]-\\d{3,4}-[0-9]{4}";
		if (Pattern.matches(phoneNumber_regex, b_contact_no)) {			
			this.b_contact_no = b_contact_no;
		} else {
			this.b_contact_no = null;
		}		
	}

	public String getDevice_pw() {
		return device_pw;
	}

	public void setDevice_pw(String device_pw) {
		this.device_pw = device_pw;
	}

	public int getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(int branch_no) {
		this.branch_no = branch_no;
	}

	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_location() {
		return branch_location;
	}

	public void setBranch_location(String branch_location) {
		this.branch_location = branch_location;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getSelected_item() {
		return selected_item;
	}

	public void setSelected_item(int selected_item) {
		this.selected_item = selected_item;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getTermsofcondition() {
		return termsofcondition;
	}

	public void setTermsofcondition(String termsofcondition) {
		this.termsofcondition = termsofcondition;
	}



}

