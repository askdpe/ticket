package com.ticket.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ticket.dao.SearchDingdan;
import com.ticket.entity.dingdan;
@Controller()
@RequestMapping("/sdd")
public class SearchDingdanController {
private SearchDingdan searchDingdan;

public SearchDingdan getSearchDingdan() {
	return searchDingdan;
}
@Autowired
public void setSearchDingdan(@Qualifier("searchdingdan")SearchDingdan searchDingdan) {
	this.searchDingdan = searchDingdan;
}
@RequestMapping("/mydingdan")
@ResponseBody  
public void  searchmydingdan(HttpServletRequest request, HttpServletResponse response) throws IOException {
	response.setCharacterEncoding("UTF-8");
	dingdan dingdan = new dingdan();
//////ȱ
	 HttpSession session=request.getSession();
	dingdan.setUname(session.getAttribute("uname").toString());
	dingdan.setPage(Integer.parseInt(request.getParameter("page"))*Integer.parseInt(request.getParameter("pagesize")));
	dingdan.setPagesize(Integer.parseInt(request.getParameter("pagesize")));
	List<HashMap<String, Object>> list=new ArrayList<>();
	List<dingdan> listremark=this.searchDingdan.GetMyDingdaninfo(dingdan);
	Iterator<dingdan> itUser=listremark.iterator();
	while (itUser.hasNext()) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		dingdan us=  itUser.next();
		map.put("dtype", us.getDtype());
		map.put("dname", us.getDname());
		map.put("dpay", us.getDpay());
		map.put("dtime", us.getDtime());
		map.put("dcomment", us.getDcomment());
		list.add(map);
	}
	PrintWriter out = response.getWriter();
	out.print(JSON.toJSONString(list));
	out.flush();
	out.close();
}
@RequestMapping("/savedingdan")
@ResponseBody  
public void  searchsavedingdan(HttpServletRequest request, HttpServletResponse response) throws IOException {
	response.setCharacterEncoding("UTF-8");
	dingdan dingdan = new dingdan();
//////ȱ
	HttpSession session=request.getSession();
	dingdan.setUname(session.getAttribute("uname").toString());
	dingdan.setPage(Integer.parseInt(request.getParameter("page"))*Integer.parseInt(request.getParameter("pagesize")));
	dingdan.setPagesize(Integer.parseInt(request.getParameter("pagesize")));
	List<HashMap<String, Object>> list=new ArrayList<>();
	List<dingdan> listremark=this.searchDingdan.GetSaveDingdaninfo(dingdan);
	Iterator<dingdan> itUser=listremark.iterator();
	while (itUser.hasNext()) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		dingdan us=  itUser.next();
		map.put("dtype", us.getDtype());
		map.put("dname", us.getDname());
		map.put("dpay", us.getDpay());
		map.put("dtime", us.getDtime());
		map.put("dcomment", us.getDcomment());
		list.add(map);
	}
	PrintWriter out = response.getWriter();
	out.print(JSON.toJSONString(list));
	out.flush();
	out.close();
}

@RequestMapping("/getmdcs")
@ResponseBody 
public int getDingdanTablePageCount(HttpServletRequest request, HttpServletResponse response){
	dingdan dingdan = new dingdan();
	HttpSession session=request.getSession();
	dingdan.setUname(session.getAttribute("uname").toString());
	double pagesize = (double) Integer.parseInt(request.getParameter("pagesize"));
	int count=this.searchDingdan.GetMaxPageNumOfAll(dingdan);
	int result = (int) Math.ceil(count / pagesize);
	return result;
	
	
}  
@RequestMapping("/getsdcs")
@ResponseBody 
public int getSDingdanTablePageCount(HttpServletRequest request, HttpServletResponse response){
	dingdan dingdan = new dingdan();
	HttpSession session=request.getSession();
	dingdan.setUname(session.getAttribute("uname").toString());
	double pagesize = (double) Integer.parseInt(request.getParameter("pagesize"));
	int count=this.searchDingdan.GetMaxPageNumOfSaveAll(dingdan);
	int result = (int) Math.ceil(count / pagesize);
	return result;
	
	
}

}
