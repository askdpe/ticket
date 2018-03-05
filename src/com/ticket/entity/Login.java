package com.ticket.entity;

public class Login {
	private String lname, lpwd, role, lregister, ltime, lpicture;

	public Login() {
	}

	public Login(String lname, String lpwd, String role, String lregister, String ltime, String lpicture) {
		this.lname = lname;
		this.lpwd = lpwd;
		this.role = role;
		this.lregister = lregister;
		this.ltime = ltime;
		this.lpicture = lpicture;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getLpwd() {
		return lpwd;
	}

	public void setLpwd(String lpwd) {
		this.lpwd = lpwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLregister() {
		return lregister;
	}

	public void setLregister(String lregister) {
		this.lregister = lregister;
	}

	public String getLtime() {
		return ltime;
	}

	public void setLtime(String ltime) {
		this.ltime = ltime;
	}

	public String getLpicture() {
		return lpicture;
	}

	public void setLpicture(String lpicture) {
		this.lpicture = lpicture;
	}
}
