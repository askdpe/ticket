package com.ticket.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/set")
public class setposition {
@RequestMapping("/setnew")	
public void setnew(HttpServletRequest request,HttpServletResponse response)throws Exception, IOException{
		int row = Integer.parseInt(request.getParameter("row"));//��
		int pai = Integer.parseInt(request.getParameter("pai"));
		String[] lst=new String[pai];
		for (int a=0;a<pai;a++) {
			lst[a]="";
			for(int i=0;i<row;i++)
			{
				lst[a]=lst[a]+"c";
			}
		}
		String json=JSON.toJSONString(lst);
		 request.setAttribute("newmap", json);
		 request.setAttribute("place", "1");
		 request.setAttribute("name", "2");
		 request.setAttribute("row", row);
		 request.setAttribute("pai", pai);
		 request.setAttribute("price", "40");
		 //System.out.println(json.toString());
		 request.getRequestDispatcher("../setposition.jsp").forward(request,response);
		
	}
@RequestMapping("/sendnew")	
public void sendnew(HttpServletRequest request,HttpServletResponse response)throws Exception, IOException{
	int row = Integer.parseInt(request.getParameter("row"));//��
	int pai = Integer.parseInt(request.getParameter("pai"));//��
	String array = request.getParameter("arr");
	if(array.equals(""))
	{
	PrintWriter out = response.getWriter();
	out.print("0");
	out.flush();
	out.close();
	}
else {
	System.out.println("��λ���ţ�");
	String[] lst=new String[pai];
	for (int a=0;a<pai;a++) {
		lst[a]="";
		for(int i=0;i<row;i++)
		{
			lst[a]=lst[a]+"c";
		}
	}
	List<String> list = JSON.parseArray(array, String.class);  
	Iterator<String> iterator=list.iterator();
	while(iterator.hasNext())
	{
		 String ant=iterator.next();
		int aString=Integer.parseInt(ant.substring(0,1));
		int bString=Integer.parseInt(ant.substring(2,3));
		StringBuffer buffer=new StringBuffer(lst[aString-1]);
		buffer.replace(bString-1, bString, "_");
		lst[aString-1]=buffer.toString();
	}
	for (String string : lst) {
		System.out.println(string);
	}
	PrintWriter out = response.getWriter();
	out.print("1");
	out.flush();
	out.close();
}

	}

}
