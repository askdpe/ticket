package com.ticket.entity;

public class friend {
int fid;
String nuname1,nuname2;
public String getNuname1() {
	return nuname1;
}
public void setNuname1(String nuname1) {
	this.nuname1 = nuname1;
}
public String getNuname2() {
	return nuname2;
}
public void setNuname2(String nuname2) {
	this.nuname2 = nuname2;
}
userinfo uname1,uname2;
public int getFid() {
	return fid;
}
public void setFid(int fid) {
	this.fid = fid;
}
public userinfo getUname1() {
	return uname1;
}
public void setUname1(userinfo uname1) {
	this.uname1 = uname1;
}
public userinfo getUname2() {
	return uname2;
}
public void setUname2(userinfo uname2) {
	this.uname2 = uname2;
}
public friend() {
	super();
	// TODO Auto-generated constructor stub
}
public friend(int fid, userinfo uname1, userinfo uname2) {
	super();
	this.fid = fid;
	this.uname1 = uname1;
	this.uname2 = uname2;
}

}
