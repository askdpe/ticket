package com.ticket.control;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ticket.dao.IUsersMapper;
import com.ticket.entity.Users;

@Controller()
@RequestMapping("/userinfo")
public class UserControl {
	IUsersMapper userMap;

	public IUsersMapper getUserMap() {
		return userMap;
	}

	@Autowired
	public void setUserMap(@Qualifier("userMapper") IUsersMapper userMap) {
		this.userMap = userMap;
	}

	@RequestMapping("/checkname")
	@ResponseBody
	public boolean isCheckNamePass(HttpServletRequest requset,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		String uname=requset.getParameter("username");
		user.setUname(uname);
		int count = this.userMap.CheckUserName(user);
		if (count==1) {
			return false;
		} 
		return true;
	}
	
	@RequestMapping("/adduser")
	@ResponseBody
	public boolean isAdd(HttpServletRequest requset,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		String uname=requset.getParameter("username");
		String name="票圈钟爱粉";
		String upwd=requset.getParameter("password");
		String uphone=requset.getParameter("telephone");
		String uemail=requset.getParameter("email");
		System.out.println(uname);
		user.setUname(uname);
		user.setName(name);
		user.setUpwd(upwd);
		user.setUphone(uphone);
		user.setUemail(uemail);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		user.setUregister(df.format(new Date()));
		int count = this.userMap.AddUser(user);
		if (count==1) {
			return true;
		} 
		return false;
	}
	@RequestMapping("/login")
	@ResponseBody
	public boolean isLoginPass(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		String uname=request.getParameter("username"), upwd=request.getParameter("password");
		user.setUname(uname);
		user.setUpwd(upwd);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		user.setUlogin(df.format(new Date()));
		int count = this.userMap.Login(user);
		int updatecount=0;
		if (count==1) {
			updatecount=this.userMap.UpdateLoginTime(user);
			if(updatecount==1){
				HttpSession session = request.getSession();
				session.setAttribute("uname", uname);
				List<Users> userList = this.userMap.SelectUserinfoByUname(user);
					Iterator<Users> it = userList.iterator();
					while (it.hasNext()) {
						user = it.next();
						session.setAttribute("name", user.getName());
						session.setAttribute("tx", user.getUpicture());
					}
				return true;
			} 
			return false;
		} 
		return false;
	}
	@RequestMapping("/updatepwd")
	@ResponseBody
	public boolean isUpdatePassword(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		String oldpwd=request.getParameter("oldpwd");
		String newpwd=request.getParameter("newpwd");
		System.out.println(uname+" "+oldpwd+" "+newpwd);
		user.setUname(uname);
		user.setUpwd(oldpwd);
		int count = this.userMap.Login(user);
		int updatecount=0;
		if (count==1) {
			user.setUpwd(newpwd);
			updatecount=this.userMap.UpdatePassword(user);
			if(updatecount==1){
				return true;
			} 
			return false;
		} 
		return false;
	}
	@RequestMapping("/getuserinfo")
	@ResponseBody
	public String GetUserInfo(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		System.out.println(uname);
		user.setUname(uname);
		Map<String,Object> userMap =new LinkedHashMap<String, Object>();
		List<Users> userList = new ArrayList<>();
		userList = this.userMap.SelectUserinfoByUname(user);
		if(userMap!=null){
			Iterator<Users> it = userList.iterator();
			while (it.hasNext()) {
				user = it.next();
				userMap.put("uname", user.getUname());
				userMap.put("my", user.getName());
				userMap.put("phone", user.getUphone());
				userMap.put("yx", user.getUemail());
				userMap.put("tx", user.getUpicture());
			}
			return JSON.toJSONString(userMap);
		}else{
			return "getinfo error";
		}
	}
	@RequestMapping("/updateuserinfo")
	@ResponseBody
	public boolean isUpdateUserInfo(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		String name=request.getParameter("name");
		String uemail=request.getParameter("yx");
		String uphone=request.getParameter("phone");
		user.setUname(uname);
		user.setName(name);
		user.setUphone(uphone);
		user.setUemail(uemail);
		int count = this.userMap.UpdateUserInfo(user);
		if (count==1) {
			return true;
		} 
		return false;
	}
	@RequestMapping("/updateuserpic")
	@ResponseBody
	public String UpdateUserPic(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Users user = new Users();
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		String uploadPath = request.getSession().getServletContext().getRealPath("/img/user");
		System.out.println(uploadPath);
		//String uploadPath = "D:/Document/Java/WebProject/EduInspectSystem/WebContent/headicon";
		File file = new File(uploadPath);
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setSizeMax(3 * 1024 * 1024);
		List<FileItem> fileItemList;
		String fileName = "";
		try {
			fileItemList = (List<FileItem>) fileUpload.parseRequest(request);
			for (FileItem item : fileItemList) {
				fileName = item.getName();
				if (fileName != null) {
					int count = fileName.lastIndexOf(".");
					String extendName = fileName.substring(count + 1);
//					Date date = new Date();
//					long newFileName = date.getTime();
					fileName = uname + "." + extendName;
					File uploadFile = new File(uploadPath + "\\" + fileName);
					item.write(uploadFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String absolutePath = uploadPath + "\\" + fileName;
//		int position = absolutePath.lastIndexOf("\\");
//		String relativePath = absolutePath.substring(position - 18);
		String upicture="img/user/"+fileName;
		System.out.println(upicture);
		user.setUname(uname);
		user.setUpicture(upicture);
		int count = this.userMap.UpdateUserPicture(user);
		String result = "";
		if (count == 1) {
			result = "成功";
		}
		return result;
	}
}
