package com.ticket.control;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/session")
public class SessionController {
		@RequestMapping("/get")
		@ResponseBody
		public Map<String, Object> get(HttpServletRequest request,HttpServletResponse response)
		{
			 HttpSession session=request.getSession();
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("name", session.getAttribute("name"));
			 map.put("tx", session.getAttribute("tx"));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			 return map;
		}
}

	