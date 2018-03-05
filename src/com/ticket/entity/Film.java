package com.ticket.entity;

import java.util.List;

public class Film {
	private String film_id,film_name,film_director,film_performer,film_type,film_area,film_time,film_language,film_synopsis,film_picture,film_comments;
	private List<Beon> listBeon;
	private Beon beon;
	private List<BeonDetail> listBeonDetail;
	private BeonDetail beonDetail;
	public List<BeonDetail> getListBeonDetail() {
		return listBeonDetail;
	}

	public void setListBeonDetail(List<BeonDetail> listBeonDetail) {
		this.listBeonDetail = listBeonDetail;
	}

	public BeonDetail getBeonDetail() {
		return beonDetail;
	}

	public void setBeonDetail(BeonDetail beonDetail) {
		this.beonDetail = beonDetail;
	}

	public Beon getBeon() {
		return beon;
	}

	public void setBeon(Beon beon) {
		this.beon = beon;
	}

	public List<Beon> getListBeon() {
		return listBeon;
	}

	public void setListBeon(List<Beon> listBeon) {
		this.listBeon = listBeon;
	}



	public String getFilm_time() {
		return film_time;
	}

	public void setFilm_time(String film_time) {
		this.film_time = film_time;
	}

	public String getFilm_id() {
		return film_id;
	}

	public void setFilm_id(String film_id) {
		this.film_id = film_id;
	}

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public String getFilm_director() {
		return film_director;
	}

	public void setFilm_director(String film_director) {
		this.film_director = film_director;
	}

	public String getFilm_performer() {
		return film_performer;
	}

	public void setFilm_performer(String film_performer) {
		this.film_performer = film_performer;
	}

	public String getFilm_type() {
		return film_type;
	}

	public void setFilm_type(String film_type) {
		this.film_type = film_type;
	}

	public String getFilm_area() {
		return film_area;
	}

	public void setFilm_area(String film_area) {
		this.film_area = film_area;
	}

	public String getFilm_language() {
		return film_language;
	}

	public void setFilm_language(String film_language) {
		this.film_language = film_language;
	}

	public String getFilm_synopsis() {
		return film_synopsis;
	}

	public void setFilm_synopsis(String film_synopsis) {
		this.film_synopsis = film_synopsis;
	}

	public String getFilm_picture() {
		return film_picture;
	}

	public void setFilm_picture(String film_picture) {
		this.film_picture = film_picture;
	}

	public String getFilm_comments() {
		return film_comments;
	}

	public void setFilm_comments(String film_comments) {
		this.film_comments = film_comments;
	}

}