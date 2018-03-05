package com.ticket.entity;

import java.util.List;

public class Hall {
	private String theatre_id,hall_id,hall_name,hallseat_info,hall_comments;
	private int upper_limit,rows,cols;
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
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	public String getHallseat_info() {
		return hallseat_info;
	}
	public void setHallseat_info(String hallseat_info) {
		this.hallseat_info = hallseat_info;
	}
	public String getHall_comments() {
		return hall_comments;
	}
	public void setHall_comments(String hall_comments) {
		this.hall_comments = hall_comments;
	}
	public int getUpper_limit() {
		return upper_limit;
	}
	public void setUpper_limit(int upper_limit) {
		this.upper_limit = upper_limit;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
}
