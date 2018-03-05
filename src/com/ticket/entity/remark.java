package com.ticket.entity;

import org.springframework.stereotype.Component;

@Component("remark")
public class remark {
public int rid,page,pagesize;
public String rplace,rtime,rscore,rcomment,uname,type,name;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
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
public remark() {
	super();
	// TODO Auto-generated constructor stub
}
public remark(int rid, String rplace, String rtime, String rscore, String rcomment) {
	super();
	this.rid = rid;
	this.rplace = rplace;
	this.rtime = rtime;
	this.rscore = rscore;
	this.rcomment = rcomment;
}
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}
public String getRplace() {
	return rplace;
}
public void setRplace(String rplace) {
	this.rplace = rplace;
}
public String getRtime() {
	return rtime;
}
public void setRtime(String rtime) {
	this.rtime = rtime;
}
public String getRscore() {
	return rscore;
}
public void setRscore(String rscore) {
	this.rscore = rscore;
}
public String getRcomment() {
	return rcomment;
}
public void setRcomment(String rcomment) {
	this.rcomment = rcomment;
}

}
