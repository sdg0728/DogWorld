package beans.userinfo;

public class UserDTO {
	private String id;    // user_id
	private String pw;
	private String name;
	private String address;
	private String email; 
	private String phone; 
	
	public UserDTO() {
		super();
	}

	public UserDTO(String id, String pw, String name, String address, String email, String phone) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + "]";
	}		
}
