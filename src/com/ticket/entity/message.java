package com.ticket.entity;

import org.springframework.stereotype.Component;

@Component("message")
public class message {
int mid;
String talker,reader,content,time,name,talkername;
public String getTalkername() {
	return talkername;
}
public void setTalkername(String talkername) {
	this.talkername = talkername;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public message() {
	super();
	// TODO Auto-generated constructor stub
}
public message(int mid, String talker, String reader, String cotent) {
	super();
	this.mid = mid;
	this.talker = talker;
	this.reader = reader;
	this.content = cotent;
}
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
public String getTalker() {
	return talker;
}
public void setTalker(String talker) {
	this.talker = talker;
}
public String getReader() {
	return reader;
}
public void setReader(String reader) {
	this.reader = reader;
}
public String getContent() {
	return content;
}
public void setContent(String cotent) {
	this.content = cotent;
}


}
