package com.ticket.control;

import java.io.IOException;
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

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.ticket.dao.SearchDongtai;
import com.ticket.entity.dongtai;
import com.ticket.entity.remark;

@Controller()
@RequestMapping("/sdt")
public class SearchDongtaiController {
SearchDongtai searchDongtai;

public SearchDongtai getSearchDongtai() {
	return searchDongtai;
}
@Autowired
public void setSearchDongtai(@Qualifier("searchdongtai") SearchDongtai searchDongtai) {
	this.searchDongtai = searchDongtai;
}
@RequestMapping("/dt")
@ResponseBody
public List<HashMap<String, Object>>  searchmydongtai(HttpServletRequest request, HttpServletResponse response) throws IOException 
{
	List<HashMap<String, Object>> list=new ArrayList<>();
	HttpSession session=request.getSession();
	String uname=session.getAttribute("uname").toString();
	List<dongtai> list2=this.searchDongtai.Getmydongtai(uname);
	Iterator<dongtai> itUser=list2.iterator();
	while (itUser.hasNext()) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		dongtai us=  itUser.next();
		map.put("tx",us.getTx());
		map.put("name",us.getName());
		map.put("content", us.getContent());
		map.put("zan", us.getZan());
		map.put("comment", us.getComment());
		map.put("id", us.getId());
		list.add(map);
	}
	return list;
}

}
