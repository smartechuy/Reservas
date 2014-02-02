package uy.com.smartech.reservas.controller.data;

public class DataUser {

	private String email;
	private String name;
	private String pass;

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DataUser [email=" + email + ", name=" + name + ", pass=" + pass
				+ "]";
	}

}
