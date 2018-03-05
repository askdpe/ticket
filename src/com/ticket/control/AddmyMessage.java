package com.ticket.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.entity.friend;
import com.ticket.entity.message;
import com.ticket.entity.userinfo;

@Controller()
@RequestMapping("/ams")
public class AddmyMessage {
com.ticket.dao.AddMessage add;
message message;



public com.ticket.dao.AddMessage getAdd() {
	return add;
}
@Autowired
public void setAdd(@Qualifier("addmessage")com.ticket.dao.AddMessage add) {
	this.add = add;
}

public message getMessage() {
	return message;
}

@Autowired
public void setMessage(@Qualifier("message")message message) {
	this.message = message;
}


		@RequestMapping("/add")
		@ResponseBody  
		public int addmymessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
			HttpSession session =request.getSession();
			this.message.setTalker(session.getAttribute("uname").toString());
			this.message.setContent(request.getParameter("content"));
			this.message.setReader(request.getParameter("reader"));
			this.add.addmessage(this.message);
			return 0;
			
		}
		@RequestMapping("/addnewfriend")
		@ResponseBody  
		public int addnewfriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
			HttpSession session =request.getSession();
			friend friend= new com.ticket.entity.friend();
			friend.setNuname1(request.getParameter("uname"));
			friend.setNuname2(session.getAttribute("uname").toString());
			if(this.add.pdnewfriend(friend)==1){
			return 0;
			}
			this.add.addnewfriend(friend);
			return 1;
		}
		
		
}
