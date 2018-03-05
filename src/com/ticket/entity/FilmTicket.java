package com.ticket.entity;



public class FilmTicket {
	private String ticket_id,theatre_id,hall_id,film_id,beon_time,seat_info,lockseat_time,uname,pay_type,pay_time,ticket_comments;
	private double pay_price;
	private int seat_num;
	private Film film;
	private Hall hall;
	private Theatre theatre;
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public String getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(String theatre_id) {
		this.theatre_id = theatre_id;
	}
	public String getHall_id() {
		return hall_id;
	}
	public void setHall_id(String hall_id) {
		this.hall_id = hall_id;
	}
	public String getFilm_id() {
		return film_id;
	}
	public void setFilm_id(String film_id) {
		this.film_id = film_id;
	}
	public String getBeon_time() {
		return beon_time;
	}
	public void setBeon_time(String beon_time) {
		this.beon_time = beon_time;
	}
	public String getSeat_info() {
		return seat_info;
	}
	public void setSeat_info(String seat_info) {
		this.seat_info = seat_info;
	}
	public String getLockseat_time() {
		return lockseat_time;
	}
	public void setLockseat_time(String lockseat_time) {
		this.lockseat_time = lockseat_time;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getTicket_comments() {
		return ticket_comments;
	}
	public void setTicket_comments(String ticket_comments) {
		this.ticket_comments = ticket_comments;
	}
	public double getPay_price() {
		return pay_price;
	}
	public void setPay_price(double pay_price) {
		this.pay_price = pay_price;
	}
	public int getSeat_num() {
		return seat_num;
	}
	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}
	
}