package com.ticket.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.dao.SearchMessage;
import com.ticket.entity.message;
import com.ticket.entity.userinfo;
@Controller()
@RequestMapping("/message")
public class SearchMessageController {
SearchMessage searchMessage;
public SearchMessage getSearchMessage() {
	return searchMessage;
}
@Autowired
public void setSearchMessage(@Qualifier("searchmessage")SearchMessage searchMessage) {
	this.searchMessage = searchMessage;
}
@RequestMapping("/ms")
@ResponseBody
public List<Map<String, Object>> searchmymessage(HttpServletRequest request,HttpServletResponse response) {
	List<Map<String, Object>> list=new ArrayList<>();
	message message=new message();
	String uname=request.getSession().getAttribute("uname").toString();
	message.setReader(uname);
    List<com.ticket.entity.message> listm = this.searchMessage.GetMessageinfo(message);
    Iterator<com.ticket.entity.message> iterator=listm.iterator();
 while (iterator.hasNext()) {
	message message2=iterator.next();
	HashMap< String, Object> map =new HashMap<>();
	map.put("time",message2.getTime());
    map.put("talker", message2.getName());
    map.put("reader", message2.getTalkername());
    map.put("uname", '"'+message2.getTalker()+'"');
    map.put("name", '"'+message2.getTalkername()+'"');
	map.put("content", message2.getContent());
	/*µÿ÷∑        £°£°£°£°£°£°£°£°£°£°£°£°£°£°£°*/
	map.put("tx", "img/user/"+message2.getTalker()+".jpg");
	map.put("ttx", "img/user/"+message2.getReader()+".jpg");
	list.add(map);
}
	return list;	
}
@RequestMapping("/friend")
@ResponseBody
public List<Map<String, Object>> searchmyfriend(HttpServletRequest request,HttpServletResponse response) {
	List<Map<String, Object>> list=new ArrayList<>();
	userinfo userinfo=new userinfo();
	userinfo.setUname(request.getSession().getAttribute("uname").toString());
	List<userinfo> list2=this.searchMessage.GetMyfriend(userinfo);
	Iterator<userinfo> iterator=list2.iterator();
	while (iterator.hasNext()) {
		userinfo userinfo2 = (userinfo) iterator.next();
		HashMap<String, Object> map=new HashMap<>();
		map.put("fname", userinfo2.getName());
		map.put("name", '"'+userinfo2.getName()+'"');
		map.put("ftx",userinfo2.getUser_picture());
		map.put("fcomments", userinfo2.getUser_comments());
		map.put("funame", '"'+userinfo2.getUname()+'"');
		list.add(map);
	}
	return list;

}
@RequestMapping("/addfriend")
@ResponseBody  
public String addmyfriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session =request.getSession();
	List<String> list= new ArrayList<>();
	userinfo userinfo=new userinfo();
	userinfo.setName("%"+request.getParameter("title")+"%");
	 List<userinfo> list2=this.searchMessage.addsearchfriend(userinfo);
	Iterator<com.ticket.entity.userinfo> iterator = list2.iterator();
	String send ="<ul>";
	while (iterator.hasNext()) {
		String name = iterator.next().getName();
		String addname="'"+name+"'";
		send+="<li onclick=choose("+addname+")>"+name+"</li>";
	}
	 send +="</ul>";
	return send;
	
}

@RequestMapping("/searchaddfriends")
@ResponseBody  
public List<Map<String,Object>> searchaddfriends(HttpServletRequest request, HttpServletResponse response) throws IOException {
	System.out.println(request.getParameter("name"));
	List<Map<String, Object>> list=new ArrayList<>();
	userinfo userinfo=new userinfo();
	userinfo.setName(request.getParameter("name"));
	List<userinfo> list2=this.searchMessage.searchfriend(userinfo);
	Iterator<userinfo> iterator=list2.iterator();
	while (iterator.hasNext()) {
		userinfo userinfo2 = (userinfo) iterator.next();
		HashMap<String, Object> map=new HashMap<>();
		map.put("fname", userinfo2.getName());
		map.put("name", '"'+userinfo2.getName()+'"');
		map.put("ftx",userinfo2.getUser_picture());
		map.put("fcomments", userinfo2.getUser_comments());
		map.put("funame", '"'+userinfo2.getUname()+'"');
		list.add(map);
	}
	return list;
	
}
}
