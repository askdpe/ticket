package com.ticket.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ticket.dao.IUserBuyFilmMapper;
import com.ticket.entity.BeonDetail;
import com.ticket.entity.Film;
import com.ticket.entity.FilmTicket;
import com.ticket.entity.Hall;
import com.ticket.entity.Theatre;

@Controller()
@RequestMapping("/userBuyFilm")
public class UserBuyFilmControl {
	IUserBuyFilmMapper userBuyFilmMapper;
	public IUserBuyFilmMapper getUserBuyFilmMap() {
		return userBuyFilmMapper;
	}
	@Autowired
	public void setUserBuyFilmMapper(@Qualifier("userBuyFilmMapper") IUserBuyFilmMapper userBuyFilmMapper) {
		this.userBuyFilmMapper = userBuyFilmMapper;
	}


	@RequestMapping("/hot")
	@ResponseBody
	public String GetHotFilmOrder(HttpServletRequest request,HttpServletResponse response) {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		List<Film> listFilm1 = this.userBuyFilmMapper.SelectNewFilmOrder();
		Iterator<Film> itFilm1 = listFilm1.iterator();
		Film fm=null;
		while (itFilm1.hasNext()) {
			fm = itFilm1.next();
			newMap.put(fm.getFilm_name(), fm.getFilm_picture());
		}
		Map<String, Object> hotMap = new LinkedHashMap<String, Object>();
		List<Film> listFilm2 = this.userBuyFilmMapper.SelectHotFilmOrder();
		Iterator<Film> itFilm2 = listFilm2.iterator();
		while (itFilm2.hasNext()) {
			fm = itFilm2.next();
			hotMap.put(fm.getFilm_name(), fm.getFilm_picture());
		}
		list.add(newMap);
		list.add(hotMap);
		return JSON.toJSONString(list);
	}
	@RequestMapping("/allbeontime")
	@ResponseBody
	public String GetAllBeonTime(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		List<Object> list = new ArrayList<>();
		BeonDetail beonDetail = new BeonDetail();
		Film film=new Film();
		String film_name=request.getParameter("filmname");
		film.setFilm_name(film_name);
		beonDetail.setFilm(film);
		Map<String, Object> filmMap = new LinkedHashMap<String, Object>();
		List<String> timeList= new ArrayList<>();
		List<BeonDetail> listBeonDetail = this.userBuyFilmMapper.SelectAllBeonTime(film_name);
		Iterator<BeonDetail> itBeonDetail = listBeonDetail.iterator();
		String time="";
		int n=1;
		while (itBeonDetail.hasNext()) {
			beonDetail = itBeonDetail.next();
			if (n==1) {
				filmMap.put("name",beonDetail.getFilm().getFilm_name());
				filmMap.put("director",beonDetail.getFilm().getFilm_director());
				filmMap.put("performer",beonDetail.getFilm().getFilm_performer());
				filmMap.put("type",beonDetail.getFilm().getFilm_type());
				filmMap.put("area",beonDetail.getFilm().getFilm_area());
				filmMap.put("language",beonDetail.getFilm().getFilm_language());
				filmMap.put("lenght",beonDetail.getFilm().getFilm_time());
				filmMap.put("synopsis",beonDetail.getFilm().getFilm_synopsis());
				filmMap.put("picture",beonDetail.getFilm().getFilm_picture());
				time=beonDetail.getBeon_time().toString().substring(0,10);
				timeList.add(time);
			}
			n++;
			if(!beonDetail.getBeon_time().toString().substring(0,10).equals(time)){
				time=beonDetail.getBeon_time().toString().substring(0,10);
				timeList.add(time);
			}
		}
		list.add(timeList);
		list.add(filmMap);
		return JSON.toJSONString(list);
	}
	@RequestMapping("/allbeondetail")
	@ResponseBody
	public String GetBeonDetailByTime(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BeonDetail beonDetail = new BeonDetail();
		Film film=new Film();
		Theatre theatre = new Theatre();
		Hall hall = new Hall();
		String film_name=request.getParameter("movename");
		String beon_time=request.getParameter("date");
		film.setFilm_name(film_name);
		beonDetail.setBeon_time(beon_time+"%");
		beonDetail.setFilm(film);
		beonDetail.setTheatre(theatre);
		beonDetail.setHall(hall);
		Map<String, Object> filmMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> timeList= new ArrayList<>();
		List<BeonDetail> listBeonDetail = this.userBuyFilmMapper.SelectBeonDetailByTime(beonDetail);
		Iterator<BeonDetail> itBeonDetail = listBeonDetail.iterator();
		String theatreName="";
		Map<String,List<Map<String,Object>>> tMap= new HashMap<String, List<Map<String,Object>>>();
		List<Map<String,Object>> tlist=new ArrayList<>();
		Map<String,Object> map=new LinkedHashMap<String, Object>();
		while (itBeonDetail.hasNext()) {
			beonDetail = itBeonDetail.next();
			theatreName=beonDetail.getTheatre().getTheatre_name().toString();
			if(!tMap.containsKey(theatreName)){
				tlist=new ArrayList<>();
				map=new LinkedHashMap<String, Object>();
				map.put("yingTing", beonDetail.getHall().getHall_name());
				map.put("time", beonDetail.getBeon_time().substring(11, 16));
				map.put("price", beonDetail.getPrice());
				tlist.add(map);
				tMap.put(theatreName, tlist);
			} else {
				tlist = tMap.get(theatreName);
				map=new LinkedHashMap<String, Object>();
				map.put("yingTing", beonDetail.getHall().getHall_name());
				map.put("time", beonDetail.getBeon_time().substring(11, 16));
				map.put("price", beonDetail.getPrice());
				tlist.add(map);
			}
		}
		return JSON.toJSONString(tMap);
	}
	
	@RequestMapping("/selectseat")
	@ResponseBody
	public boolean GetSeatinfo(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BeonDetail beonDetail = new BeonDetail();
		Film film=new Film();
		Theatre theatre = new Theatre();
		Hall hall = new Hall();
		String film_name=request.getParameter("filmname");
		String theatre_name=request.getParameter("theatrename");
		String hall_name=request.getParameter("hallname");
		String beon_time=request.getParameter("datetime");
		String price=request.getParameter("price");
		film.setFilm_name(film_name);
		theatre.setTheatre_name(theatre_name);
		hall.setHall_name(hall_name);
		beonDetail.setBeon_time(beon_time);
		beonDetail.setFilm(film);
		beonDetail.setTheatre(theatre);
		beonDetail.setHall(hall);
		String hall_seatinfo="";
		System.out.println(theatre_name+" "+ hall_name);
		hall_seatinfo = this.userBuyFilmMapper.SelectHallSeatinfo(theatre_name, hall_name);
		System.out.println(hall_seatinfo);
		ArrayList<String> listHallSeat=new ArrayList<String>();
		String[] strshall=hall_seatinfo.split("/");
		for(int i=0,len=strshall.length;i<len;i++){
			listHallSeat.add(strshall[i]);
		}
		String jsonHallSeat=JSON.toJSONString(listHallSeat);
//		System.out.println(jsonHallSeat);
		String beon_seatinfo="";
		beon_seatinfo = this.userBuyFilmMapper.SelectBeonSeatinfo(beonDetail);
		System.out.println(beon_seatinfo);
		if(beon_seatinfo!=null && !beon_seatinfo.equals("")){
//			System.out.println("-----------");
//			System.out.println(beon_seatinfo);
//			System.out.println("-----------");
			ArrayList<String> list=new ArrayList<String>();
			String[] strs=beon_seatinfo.split("/");
			for(int i=0;i<strs.length;i++){
				String seat_info[]=strs[i].toString().split(",");
				if(seat_info[1].equals("0") || seat_info[1].equals("2")){
					list.add(seat_info[0]);
				}
			}
			System.out.println(list.size()+"  list size");
			String[] array = new String[list.size()];
			list.toArray(array);
			String json=JSON.toJSONString(array);
			System.out.println(json);
			HttpSession session = request.getSession();
			session.setAttribute("selectedarr", json);
			if (session.getAttribute("chooseflush") == null) {
				session.setAttribute("chooseflush", "1");
			} else {
				if (session.getAttribute("chooseflush").equals("1")) {
					session.setAttribute("chooseflush", "0");
				} else {
					session.setAttribute("chooseflush", "1");
				}
			}
			session.setAttribute("jsonHallSeat", jsonHallSeat);
			session.setAttribute("theatre_name", theatre_name);
			session.setAttribute("hall_name", hall_name);
			session.setAttribute("film_name", film_name);
			session.setAttribute("beontime", beon_time.toString().substring(0, 16));
			session.setAttribute("price", price);
			return true;
		}
		return false;
	}
	
	@RequestMapping("/submitseat")
	@ResponseBody
	public String GetSelectedSeat(HttpServletRequest request,HttpServletResponse response)throws IOException{
		HttpSession session = request.getSession();
		BeonDetail beonDetail = new BeonDetail();
		Film film=new Film();
		Theatre theatre = new Theatre();
		Hall hall = new Hall();
		String uname = (String) session.getAttribute("uname");
		String film_name = (String) session.getAttribute("film_name");
		String theatre_name = (String) session.getAttribute("theatre_name");
		String hall_name = (String) session.getAttribute("hall_name");
		String beon_time = (String) session.getAttribute("beontime")+":00";
		String price = (String) session.getAttribute("price");
		String selectedarr = request.getParameter("selectedarr");
		String tickects_num = request.getParameter("tickects_num");
		session.setAttribute("selectedarr", selectedarr);
		session.setAttribute("tickects_num", tickects_num);
		System.out.println(film_name+","+theatre_name+","+hall_name+","+beon_time+","+price+","+selectedarr+","+tickects_num);
		String seat="";
		film.setFilm_name(film_name);
		theatre.setTheatre_name(theatre_name);
		hall.setHall_name(hall_name);
		beonDetail.setBeon_time(beon_time);
		beonDetail.setFilm(film);
		beonDetail.setTheatre(theatre);
		beonDetail.setHall(hall);
		String beon_seatinfo = this.userBuyFilmMapper.SelectBeonSeatinfo(beonDetail);
		String oldbeon_seatinfo=beon_seatinfo;
		System.out.println(beon_seatinfo);
		String seat_info2="";
		if(selectedarr==null){
			return "0";
		}else {
			System.out.println("购票");
			List list = JSON.parseArray(selectedarr, String.class);
			if(list.size()==Integer.parseInt(tickects_num)){
				Iterator<String> iterator=list.iterator();
				while(iterator.hasNext()){
					seat=iterator.next();
					seat_info2=seat_info2+seat+"/";
					System.out.println(seat_info2);
					String[] strs=beon_seatinfo.split("/");
					for(int i=0;i<strs.length;i++){
						String seat_info[]=strs[i].toString().split(",");
						if(seat_info[0].equals(seat)){
							if(seat_info[1].equals("0")){
								System.out.println("错误:"+seat+" 已售出");
								return "错误:"+seat+" 已售出";
							}else if(seat_info[1].equals("2")){
								System.out.println("错误:"+seat+" 已被锁定，等待其他人支付");
								return "错误:"+seat+" 已被锁定，等待其他人支付";
							}else if(seat_info[1].equals("1")){
								StringBuilder strBuilder = new StringBuilder(beon_seatinfo);
								strBuilder.setCharAt(beon_seatinfo.indexOf(seat)+seat.length()+1, '2');
								beon_seatinfo=strBuilder.toString();
							}else{
								System.out.println("错误："+seat+" 未找到");
								return "错误："+seat+" 未找到";
							}
						}
					}
					System.out.println(beon_seatinfo);
				}
				//beon_seatinfo插入 beon_detail
				beonDetail.setBeon_seatinfo(beon_seatinfo);
				int updatecount= this.userBuyFilmMapper.UpdateLockSeat(beonDetail);
				if(updatecount == 1){
					FilmTicket filmTicket = new FilmTicket();
					filmTicket.setTheatre(theatre);
					filmTicket.setFilm(film);
					filmTicket.setHall(hall);
					filmTicket.setUname(uname);
					filmTicket.setBeon_time(beon_time);
					int seat_num= Integer.parseInt(tickects_num);
					double pricedouble=Double.parseDouble(price);
					double pay_price = seat_num*pricedouble;
					SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					Date nowtime = new Date();
					String lockseat_time= df.format(nowtime);
					Date afterDate = new Date(nowtime .getTime() + 30000);//计时器
					String unlockseat_time = df.format(afterDate);
					System.out.println(unlockseat_time);
					session.setAttribute("unlockseat_time", unlockseat_time);
					filmTicket.setSeat_num(seat_num);
					filmTicket.setPay_price(pay_price);
					filmTicket.setLockseat_time(lockseat_time);
					filmTicket.setSeat_info(seat_info2);
					UUID uuid = UUID.randomUUID();
					String[] randomString = uuid.toString().split("-");
					String ticket_id = randomString[4];
					filmTicket.setTicket_id(ticket_id);;
					session.setAttribute("ticket_id", ticket_id);
					int addcount=this.userBuyFilmMapper.AddWaitPay(filmTicket);
					if(addcount==1){
						return "1";
					}else{
						return "0";
					}
				}else{
					return "0";
				}
			}else{
				return "0";
			}
		}
	}
	@RequestMapping("/getfilmpay")
	@ResponseBody
	public String GetPayInfo(HttpServletRequest request,HttpServletResponse response)throws IOException{
		HttpSession session = request.getSession();
		String ticket_id = (String) session.getAttribute("ticket_id");
		String film_name = (String) session.getAttribute("film_name");
		String theatre_name = (String) session.getAttribute("theatre_name");
		String hall_name = (String) session.getAttribute("hall_name");
		String beon_time = (String) session.getAttribute("beontime")+":00";
		String price = (String) session.getAttribute("price");
		String selectedarr = (String) session.getAttribute("selectedarr");
		String tickects_num = (String) session.getAttribute("tickects_num");
		String uname = (String) session.getAttribute("uname");
		String unlockseat_time = (String) session.getAttribute("unlockseat_time");
		System.out.println(film_name+","+theatre_name+","+hall_name+","+beon_time+","+price+","+selectedarr+","+tickects_num);
		System.out.println("pay");
		String seat="";
		List list = JSON.parseArray(selectedarr, String.class);
		List selectedlist = new ArrayList<>();
		if(list.size()==Integer.parseInt(tickects_num)){
			Iterator<String> iterator=list.iterator();
			while(iterator.hasNext()){
				seat=iterator.next();
				System.out.println(seat);
				String[] strs=seat.split("_");
				seat=strs[0]+"排"+strs[1]+"座";
				selectedlist.add(seat);
			}
			Map<String,Object> paymap = new LinkedHashMap<>();
			paymap.put("unlockseat_time", unlockseat_time);
			paymap.put("uname", uname);
			paymap.put("ticket_id", ticket_id);
			paymap.put("film_name", film_name);
			paymap.put("theatre_name", theatre_name);
			paymap.put("hall_name", hall_name);
			paymap.put("beon_time", beon_time);
			paymap.put("tickects_num", tickects_num);
			paymap.put("price", price);
			paymap.put("selectedlist", selectedlist);
			System.out.println(paymap);
			return JSON.toJSONString(paymap);
		}else{
			return "0";
		}	
	}
	@RequestMapping("/getfilmticketinfo")
	@ResponseBody
	public String GetFilmPayInfo(HttpServletRequest request,HttpServletResponse response)throws IOException, ParseException{
		String ticket_id=request.getParameter("ticket_id");
		String pay_type=request.getParameter("paytype");
		String pay_time=request.getParameter("pay_time");
		FilmTicket filmTicket = new FilmTicket();
		filmTicket.setTicket_id(ticket_id);
		filmTicket.setPay_type(pay_type);
		filmTicket.setPay_time(pay_time);
		if(pay_time==null){
			return "支付失败";
		}
		FilmTicket filmTicket2 = null;
		filmTicket2 = this.userBuyFilmMapper.SelectFilmPayInfo(filmTicket);
		if(filmTicket2==null){
			return "订单已取消";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date datepay_time = sdf.parse(pay_time);  
		Date datelock_time = sdf.parse(filmTicket2.getLockseat_time().substring(0,19));  
		long datediv = datepay_time.getTime() - datelock_time.getTime();  
		if(!filmTicket2.getPay_type().equals("wait")){
			return "已支付成功";
		}
		if (datediv>300000) {
			int delcount = this.userBuyFilmMapper.DeleteFilmPayInfo(ticket_id);
			if(delcount==1){
				return "支付超时，订单已取消";
			}else{
				return "支付超时，订单取消失败";
			}
		}else{
			int updatecount=this.userBuyFilmMapper.UpdateFilmPayInfo(filmTicket);
			if(updatecount==1){
				filmTicket2 = this.userBuyFilmMapper.SelectFilmPayInfo(filmTicket);
				Map<String,Object> paymap = new LinkedHashMap<>();
				paymap.put("ticket_id", filmTicket2.getTicket_id());
				paymap.put("theatre_id", filmTicket2.getTheatre_id());
				paymap.put("hall_id", filmTicket2.getHall_id());
				paymap.put("film_id", filmTicket2.getFilm_id());
				paymap.put("beon_time", filmTicket2.getBeon_time().toString().substring(0, 16));
				paymap.put("seat_info", filmTicket2.getSeat_info());
				paymap.put("uname", filmTicket2.getUname());
				paymap.put("pay_type", pay_type);
				paymap.put("pay_time", pay_time);
				paymap.put("pay_price", filmTicket2.getPay_price());
				paymap.put("seat_num", filmTicket2.getSeat_num());
				System.out.println(paymap);
				return JSON.toJSONString(paymap);
			}else{
				return "支付失败";
			}
		}
	}
	@RequestMapping("/getfilminfo")
	@ResponseBody
	public String GetFilmInfo(HttpServletRequest request,HttpServletResponse response)throws IOException{
		HttpSession session = request.getSession();
		String film_name = (String) session.getAttribute("film_name");
		System.out.println("pay");
		Map<String, Object> newMap = new LinkedHashMap<String, Object>();
		List<Film> listFilm = this.userBuyFilmMapper.SelectFilmInfo(film_name);
		Iterator<Film> itFilm = listFilm.iterator();
		Film fm=null;
		while (itFilm.hasNext()) {
			fm = itFilm.next();
			newMap.put("film_picture", fm.getFilm_picture());
			newMap.put("film_language", fm.getFilm_language());
			newMap.put("film_lenght", fm.getFilm_time());
		}
		System.out.println(JSON.toJSONString(newMap));
		return JSON.toJSONString(newMap);
	}
	@RequestMapping("/canclefpayinfo")
	@ResponseBody
	public String DeleteFilmPayInfo(HttpServletRequest request,HttpServletResponse response)throws IOException{
		String ticket_id= request.getParameter("ticket_id");
		System.out.println(ticket_id);
		FilmTicket filmTicket = new FilmTicket();
		filmTicket.setTicket_id(ticket_id);
		FilmTicket filmTicket2 = null;
		filmTicket2 = this.userBuyFilmMapper.SelectFilmPayInfo(filmTicket);
		if(filmTicket2==null){
			return "订单已取消";
		}
		System.out.println("---------------"+filmTicket2.getSeat_info());
		int delcount = this.userBuyFilmMapper.DeleteFilmPayInfo(ticket_id);
		if(delcount==1){
			/////////////////
		/*	BeonDetail beonDetail = new BeonDetail();
			String beon_seatinfo=null;
			beonDetail=this.userBuyFilmMapper.SelectBeonSeatinfoByTicketId(ticket_id);
			if(beonDetail==null || beon_seatinfo==null){
				return "解锁座位失败";
			}
			beon_seatinfo=beonDetail.getBeon_seatinfo();
			System.out.println(beon_seatinfo);
			String[] unlockdarr=filmTicket2.getSeat_info().split("/");
			String seat="";
			List list = Arrays.asList(unlockdarr);
				Iterator<String> iterator=list.iterator();
				while(iterator.hasNext()){
					seat=iterator.next();
					System.out.println(seat);
					String[] strs=beon_seatinfo.split("/");
					for(int i=0;i<strs.length;i++){
						String seat_info[]=strs[i].toString().split(",");
						if(seat_info[0].equals(seat)){
							if(seat_info[1].equals("0")){
								System.out.println("错误:"+seat+" 已售出");
								return "解锁座位失败";
							}else if(seat_info[1].equals("2")){
								StringBuilder strBuilder = new StringBuilder(beon_seatinfo);
								strBuilder.setCharAt(beon_seatinfo.indexOf(seat)+seat.length()+1, '1');
								beon_seatinfo=strBuilder.toString();
							}else{
								System.out.println("错误："+seat+" 未找到");
								return "解锁座位失败";
							}
						}
					}
					System.out.println(beon_seatinfo);
				}
				//beon_seatinfo插入 beon_detail
				beonDetail.setBeon_seatinfo(beon_seatinfo);
				int updatecount= this.userBuyFilmMapper.UpdateLockSeat(beonDetail);
				if(updatecount == 1){
					System.out.println("解锁成功");
					return "取消支付订单成功";
				}else{
					return "取消支付订单失败";
				} */	
			return "取消支付订单成功";
			//////////////////
		}else{
			return "取消支付订单失败";
		}
	}
}
