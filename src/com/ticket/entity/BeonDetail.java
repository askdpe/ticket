package com.ticket.entity;

import java.util.List;

public class BeonDetail {
	private String theatre_id,film_id,hall_id,beon_time,version,beon_seatinfo,beon_comments;
	private double price;
	private int beon_seatnum;
	private Film film;
	private Theatre theatre;
	private Hall hall;
	private FilmTicket filmTicket;
	public FilmTicket getFilmTicket() {
		return filmTicket;
	}
	public void setFilmTicket(FilmTicket filmTicket) {
		this.filmTicket = filmTicket;
	}
	public String getBeon_seatinfo() {
		return beon_seatinfo;
	}
	public void setBeon_seatinfo(String beon_seatinfo) {
		this.beon_seatinfo = beon_seatinfo;
	}
	public int getBeon_seatnum() {
		return beon_seatnum;
	}
	public void setBeon_seatnum(int beon_seatnum) {
		this.beon_seatnum = beon_seatnum;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(String theatre_id) {
		this.theatre_id = theatre_id;
	}
	public String getFilm_id() {
		return film_id;
	}
	public void setFilm_id(String film_id) {
		this.film_id = film_id;
	}
	public String getHall_id() {
		return hall_id;
	}
	public void setHall_id(String hall_id) {
		this.hall_id = hall_id;
	}
	public String getBeon_time() {
		return beon_time;
	}
	public void setBeon_time(String beon_time) {
		this.beon_time = beon_time;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBeon_comments() {
		return beon_comments;
	}
	public void setBeon_comments(String beon_comments) {
		this.beon_comments = beon_comments;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
