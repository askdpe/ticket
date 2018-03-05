package com.ticket.entity;

public class Theatre {
	private String theatre_id, theatre_name, theatre_address, zone_id, theatre_head, theatre_phone, theatre_email,
			theatre_account, theatre_pwd, theatre_picture, theatre_licence, theatre_status, theatre_registertime;

	public Theatre() {
	}

	public Theatre(String theatre_id, String theatre_name, String theatre_address, String zone_id, String theatre_head,
			String theatre_phone, String theatre_email, String theatre_account, String theatre_pwd,
			String theatre_picture, String theatre_licence, String theatre_status, String theatre_registertime) {
		this.theatre_id = theatre_id;
		this.theatre_name = theatre_name;
		this.theatre_address = theatre_address;
		this.zone_id = zone_id;
		this.theatre_head = theatre_head;
		this.theatre_phone = theatre_phone;
		this.theatre_email = theatre_email;
		this.theatre_account = theatre_account;
		this.theatre_pwd = theatre_pwd;
		this.theatre_picture = theatre_picture;
		this.theatre_licence = theatre_licence;
		this.theatre_status = theatre_status;
	}

	public String getTheatre_licence() {
		return theatre_licence;
	}

	public void setTheatre_licence(String theatre_licence) {
		this.theatre_licence = theatre_licence;
	}

	public String getTheatre_registertime() {
		return theatre_registertime;
	}

	public void setTheatre_registertime(String theatre_registertime) {
		this.theatre_registertime = theatre_registertime;
	}

	public String getTheatre_picture() {
		return theatre_picture;
	}

	public void setTheatre_picture(String theatre_picture) {
		this.theatre_picture = theatre_picture;
	}

	public String getTheatre_email() {
		return theatre_email;
	}

	public void setTheatre_email(String theatre_email) {
		this.theatre_email = theatre_email;
	}

	public String getTheatre_id() {
		return theatre_id;
	}

	public void setTheatre_id(String theatre_id) {
		this.theatre_id = theatre_id;
	}

	public String getTheatre_name() {
		return theatre_name;
	}

	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}

	public String getTheatre_address() {
		return theatre_address;
	}

	public void setTheatre_address(String theatre_address) {
		this.theatre_address = theatre_address;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}

	public String getTheatre_head() {
		return theatre_head;
	}

	public void setTheatre_head(String theatre_head) {
		this.theatre_head = theatre_head;
	}

	public String getTheatre_phone() {
		return theatre_phone;
	}

	public void setTheatre_phone(String theatre_phone) {
		this.theatre_phone = theatre_phone;
	}

	public String getTheatre_account() {
		return theatre_account;
	}

	public void setTheatre_account(String theatre_account) {
		this.theatre_account = theatre_account;
	}

	public String getTheatre_pwd() {
		return theatre_pwd;
	}

	public void setTheatre_pwd(String theatre_pwd) {
		this.theatre_pwd = theatre_pwd;
	}

	public String getTheatre_status() {
		return theatre_status;
	}

	public void setTheatre_status(String theatre_status) {
		this.theatre_status = theatre_status;
	}
}
