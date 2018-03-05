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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ticket.dao.SearchRemark;
import com.ticket.entity.remark;
@Controller()
@RequestMapping("/srmk")
public class SearchRemarkController {
public SearchRemarkController() {
		super();
		// TODO Auto-generated constructor stub
	}

private SearchRemark searchRemark;
@Autowired
public void setSearchRemark(@Qualifier("searchremark") SearchRemark searchRemark) {
	this.searchRemark = searchRemark;
}

public SearchRemark getSearchRemark() {
	return searchRemark;
}
@RequestMapping("/remark")
public void  searchremark(HttpServletRequest request, HttpServletResponse response) throws IOException {
	response.setCharacterEncoding("UTF-8");
	remark myremark = new remark();
//////ȱ
	HttpSession session=request.getSession();
	myremark.setUname(session.getAttribute("uname").toString());
	myremark.setType(request.getParameter("type"));
	//myremark.setUname((String) request.getAttribute("uname"));
	myremark.setPage(Integer.parseInt(request.getParameter("page"))*Integer.parseInt(request.getParameter("pagesize")));
	myremark.setPagesize(Integer.parseInt(request.getParameter("pagesize")));
	List<HashMap<String, Object>> list=new ArrayList<>();
	List<remark> listremark=this.searchRemark.GetRemarkinfo(myremark);
	Iterator<remark> itUser=listremark.iterator();
	while (itUser.hasNext()) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		remark us=  itUser.next();
		map.put("rid", Integer.toString(us.getRid()));
		map.put("rplace", us.getRplace());
		map.put("rscore", us.getRscore());
		map.put("rtime", us.getRtime());
		map.put("rcomment", us.getRcomment());
		list.add(map);
	}
	PrintWriter out = response.getWriter();
	out.print(JSON.toJSONString(list));
	out.flush();
	out.close();
}
@RequestMapping("/getpc")
@ResponseBody 
public int getRemarkTablePageCount(HttpServletRequest request, HttpServletResponse response){
	remark myremark = new remark();
//////ȱ
	HttpSession session=request.getSession();
	myremark.setUname(session.getAttribute("uname").toString());
	myremark.setType(request.getParameter("type"));
	double pagesize = (double) Integer.parseInt(request.getParameter("pagesize"));
	int count=this.searchRemark.GetMaxPageNumOfAll(myremark);
	int result = (int) Math.ceil(count / pagesize);
	return result;
	
	
}      
}
