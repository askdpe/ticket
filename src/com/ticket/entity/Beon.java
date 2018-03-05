package com.ticket.entity;


public class Beon {
	private String theatre_id,film_id,be_on,be_off,beon_comments;
	private Film film;

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

	public String getBe_on() {
		return be_on;
	}

	public void setBe_on(String be_on) {
		this.be_on = be_on;
	}

	public String getBe_off() {
		return be_off;
	}

	public void setBe_off(String be_off) {
		this.be_off = be_off;
	}

	public String getBeon_comments() {
		return beon_comments;
	}

	public void setBeon_comments(String beon_comments) {
		this.beon_comments = beon_comments;
	}
}
