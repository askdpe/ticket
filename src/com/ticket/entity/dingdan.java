package com.ticket.entity;

public class dingdan {
int did,page,pagesize;
String uname,dtype,dname,dpay,dtime,dcomment,type;
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getDid() {
	return did;
}
public void setDid(int did) {
	this.did = did;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getPagesize() {
	return pagesize;
}
public void setPagesize(int pagesize) {
	this.pagesize = pagesize;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getDtype() {
	return dtype;
}
public void setDtype(String dtype) {
	this.dtype = dtype;
}
public String getDname() {
	return dname;
}
public void setDname(String dname) {
	this.dname = dname;
}
public String getDpay() {
	return dpay;
}
public void setDpay(String dpay) {
	this.dpay = dpay;
}
public String getDtime() {
	return dtime;
}
public void setDtime(String dtime) {
	this.dtime = dtime;
}
public String getDcomment() {
	return dcomment;
}
public void setDcomment(String dcomment) {
	this.dcomment = dcomment;
}
public dingdan(int did, int page, int pagesize, String uname, String dtype, String dname, String dpay, String dtime,
		String dcomment) {
	super();
	this.did = did;
	this.page = page;
	this.pagesize = pagesize;
	this.uname = uname;
	this.dtype = dtype;
	this.dname = dname;
	this.dpay = dpay;
	this.dtime = dtime;
	this.dcomment = dcomment;
}
public dingdan() {
	super();
	// TODO Auto-generated constructor stub
}

}
