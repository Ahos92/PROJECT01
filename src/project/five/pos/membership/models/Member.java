package project.five.pos.membership.models;

public class Member {
	private long id;
	private String username;
	private String password;
	private String name;
	private String Birth;
	private String phone;
	
	public Member() {
	
	}
	
	public Member(long id, String username, String password, String name, String Birth, String phone) {	
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.Birth = Birth;
		this.phone = phone;
		
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

	
	

}
