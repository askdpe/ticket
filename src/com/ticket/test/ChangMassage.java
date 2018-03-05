package com.ticket.test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cm")
public class ChangMassage {
@RequestMapping("/mcm")
@ResponseBody
public Map<String, Object>  changemassage(HttpServletRequest request,HttpServletResponse response) throws Exception {
	response.setCharacterEncoding("UTF-8");
	List<Map<String, Object>> list =new ArrayList<>();
	Map<String, Object> map=new HashMap<String, Object>();
   // map.put("uname","anna");
	HttpSession session=request.getSession();
	map.put("uname",session.getAttribute("uname"));
    map.put("my","ø…∞Æµƒ–°…’»‚");
    map.put("phone",1356985498);
    map.put("yx", "iuwq@168.com");

    return map;
}
@RequestMapping("/cpwd")
@ResponseBody
public int changepwd(HttpServletRequest request) {
	System.out.println(request.getParameter("name"));
	return 1;
}
}
