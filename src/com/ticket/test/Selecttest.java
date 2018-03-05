package com.ticket.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/select")
public class Selecttest {

@RequestMapping("/slt")	
@ResponseBody 
public void select(HttpServletRequest request,HttpServletResponse response)throws Exception, IOException{
	String[] array=new String[10];
	Random r = new Random();
	for (int i = 0; i < 10; i++) {
			array[i]=Integer.toString(r.nextInt(10+1))+"_"+Integer.toString(r.nextInt(10+1));
	}
	 String json=JSON.toJSONString(array);
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 request.setAttribute("arr", json);
	 request.setAttribute("place", "嘉禾影院");
	 request.setAttribute("name", "前任3");
	 request.setAttribute("time", df.format(new Date()));
	 request.setAttribute("price", "40");
	 request.getRequestDispatcher("../choose.jsp").forward(request,response);
	
}

@RequestMapping("/sltok")	
public void selectq(HttpServletRequest request,HttpServletResponse response)throws IOException{
	String row = request.getParameter("row");
	String pai = request.getParameter("pai");
	String array = request.getParameter("arr");
if(array.equals(""))
	{
	PrintWriter out = response.getWriter();
	out.print("0");
	out.flush();
	out.close();
	}
else {
	System.out.println("所选座位：");
	List<String> list = JSON.parseArray(array, String.class);  
	Iterator<String> iterator=list.iterator();
	while(iterator.hasNext())
	{
		System.out.println(iterator.next());
	}
	PrintWriter out = response.getWriter();
	out.print("1");
	out.flush();
	out.close();
}

	
}
}
