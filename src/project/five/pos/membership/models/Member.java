package project.five.pos.membership.models;

public class Member {
	private long id;
	private String username;
	private String password;
	private String name;
	private String Birth;
	private String phone;
	
	private int amount;
	private String grade;
	private double discount_pct;
	private double save_pct;
	
	public Member() {
	
	}
	
	public Member(long id, String username, String password, String name, String Birth, String phone,
			int amount, String grade, double discount_pct, double save_pct) {	
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.Birth = Birth;
		this.phone = phone;
		
		this.amount = amount;
		this.grade = grade;
		this.discount_pct = discount_pct;
		this.save_pct = save_pct;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return Birth;
	}

	public void setBirth(String Birth) {
		this.Birth = Birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	// 12.09 추가사항 ----------------------------------------------
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public double getDiscount_pct() {
		return discount_pct;
	}
	
	public void setDiscount_pct(double discount_pct) {
		this.discount_pct = discount_pct;
	}
	
	public double getSave_pct() {
		return save_pct;
	}
	
	public void setSave_pct(double save_pct) {
		this.save_pct = save_pct;
	}
	
	

}
