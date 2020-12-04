package project.five.pos.sale;

public class SaleDTO {

	// cart Table
	private int cart_no;
	private int order_no; // 12/04 추가
	private int product_no;
	private int selected_item;
	private int total_price; // 12/04 추가
	
	// pos Table
	private int device_id;
	private int payment_id;
	private int customer_id;
	// int cart_no

	// test product table
	// int product_no;
	private String product_name;
	private int product_price;
	private int product_count;
	private String product_category;
	private String termsofcondition;
	// 테스트용 추가 컬럼
	private int order_count; // 12/04 추가
	

	
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
	
	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
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
	
	public int getPayment_id() {
		return payment_id;
	}
	
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
