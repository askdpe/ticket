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

import com.ticket.entity.dongtai;



@Controller()
@RequestMapping("/adt")
public class AddDongtai {
com.ticket.dao.AddDongtai add;
	
	
	public com.ticket.dao.AddDongtai getAdd() {
	return add;
}

@Autowired
public void setAdd(@Qualifier("adddongtai")com.ticket.dao.AddDongtai add) {
	this.add = add;
}


	@RequestMapping("/add")
	@ResponseBody  
	public int adddongtai(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		dongtai dongtai=new dongtai();
		dongtai.setContent(request.getParameter("content"));
		dongtai.setUname(session.getAttribute("uname").toString());
		this.add.AddMydongtai(dongtai);
		return 0;
		
	}
}
