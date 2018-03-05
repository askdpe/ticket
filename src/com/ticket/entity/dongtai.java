package com.ticket.entity;

public class dongtai {
String uname,content,time,comment,name,tx;
public String getTx() {
	return tx;
}
public void setTx(String tx) {
	this.tx = tx;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
int id,zan;
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getZan() {
	return zan;
}
public void setZan(int zan) {
	this.zan = zan;
}
public dongtai(String uname, String content, String time, String comment, int id, int zan) {
	super();
	this.uname = uname;
	this.content = content;
	this.time = time;
	this.comment = comment;
	this.id = id;
	this.zan = zan;
}
public dongtai() {
	super();
	// TODO Auto-generated constructor stub
}

}
