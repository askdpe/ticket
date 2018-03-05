package com.ticket.control;

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

import com.ticket.dao.Addpinglun;
import com.ticket.entity.remark;




@Controller
@RequestMapping("/apl")
public class AddPinglunController {
Addpinglun addpinglun;

remark remark;
public Addpinglun getAddpinglun() {
	return addpinglun;
}
@Autowired
public void setAddpinglun(@Qualifier("addpinglun")Addpinglun addpinglun) {
	this.addpinglun = addpinglun;
}
public remark getRemark() {
	return remark;
}
@Autowired
public void setRemark(@Qualifier("remark")remark remark) {
	this.remark = remark;
}
@RequestMapping("/add")
@ResponseBody
public int addmypinglu(HttpServletRequest request,HttpServletResponse response) {
	HttpSession session=request.getSession();
	this.remark.setRcomment(request.getParameter("content"));
	 if(remark.getRcomment()=="")
    	 return 0;
	this.remark.setRscore(request.getParameter("score"));
	this.remark.setRplace(request.getParameter("filmname"));
	//this.remark.setUname(session.getAttribute("uname").toString());
	this.remark.setUname(session.getAttribute("uname").toString());
	this.addpinglun.Addmypinglun(remark);
    
	return 1;
	
}

@RequestMapping("/search")
@ResponseBody
public List<Map<String, Object>>  Searchfilm(HttpServletRequest request,HttpServletResponse response)	
{
	List<Map<String, Object>> list=new ArrayList<>();
	this.remark.setRplace(request.getParameter("rplace"));
	List<remark> list2=this.addpinglun.SearchPinglunofflim(this.remark);
	Iterator<remark> iterator=list2.iterator();
	while (iterator.hasNext()) {
		HashMap<String, Object> map =new HashMap<>();
		remark myremark =  iterator.next();
		map.put("name",myremark.getName());
		map.put("time", myremark.getRtime());
		map.put("content", myremark.getRcomment());
		map.put("score", myremark.getRscore());
		list.add(map);
	}	
	return list;
	
}
}
