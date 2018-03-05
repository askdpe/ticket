package com.ticket.control;

import java.io.UnsupportedEncodingException;
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

import com.ticket.entity.Login;
import com.ticket.dao.ILoginMapper;

@Controller
@RequestMapping("/login")
public class LoginControl {
	ILoginMapper loginMapper;
	Login login = new Login();

	public ILoginMapper getLoginMapper() {
		return loginMapper;
	}

	@Autowired
	public void setLoginMapper(@Qualifier("loginMapper") ILoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	@RequestMapping("/verifyLoginer")
	@ResponseBody
	public String verifyLoginer(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String userID = request.getParameter("username");
		System.out.println("当前要进行登录用户ID ：" + userID);
		List<Login> logins = this.loginMapper.isLoginer(userID, request.getParameter("password"));
		System.out.println("查找到匹配用户的ID ：" + logins.size());
		if (logins.size() > 0) {
			// 记录当前用户
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", userID);

			// 记录最新登录时间
			this.loginMapper.recordLoginTime((String) session.getAttribute("currentUser"));

			Iterator<Login> itLoginer = logins.iterator();
			String loginRole = "";
			if (itLoginer.hasNext()) {
				Login login = (Login) itLoginer.next();
				loginRole = login.getRole();
			}
			// 如果存在该用户，返回用户的身份，影院“theatre”
			return loginRole;
		} else {
			return "";
		}
	}
}
