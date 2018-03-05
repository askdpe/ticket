package com.ticket.entity;

public class userinfo {
String uname,user_picture,user_comments,name;

public String getUname() {
	return uname;
}

public void setUname(String uname) {
	this.uname = uname;
}

public String getUser_picture() {
	return user_picture;
}

public void setUser_picture(String user_picture) {
	this.user_picture = user_picture;
}

public String getUser_comments() {
	return user_comments;
}

public void setUser_comments(String user_comments) {
	this.user_comments = user_comments;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public userinfo(String uname, String user_picture, String user_comments, String name) {
	super();
	this.uname = uname;
	this.user_picture = user_picture;
	this.user_comments = user_comments;
	this.name = name;
}

public userinfo() {
	super();
	// TODO Auto-generated constructor stub
}

}
