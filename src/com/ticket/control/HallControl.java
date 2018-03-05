package com.ticket.control;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.ticket.entity.Hall;
import com.ticket.dao.IHallMapper;

@Controller
@RequestMapping("/hall")
public class HallControl {
	IHallMapper hallMapper;
	Hall hall = new Hall();

	public IHallMapper getHallMapper() {
		return hallMapper;
	}

	@Autowired
	public void setHallMapper(@Qualifier("hallMapper") IHallMapper hallMapper) {
		this.hallMapper = hallMapper;
	}

	@RequestMapping("/addNewHall")
	@ResponseBody
	public boolean addNewHall(HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		hall.setTheatre_id((String) session.getAttribute("currentUser"));

		// 为影厅生成唯一标识码UUID
		UUID uuid = UUID.randomUUID();
		String[] uid = uuid.toString().split("-");
		String hallId = uid[1];

		hall.setHall_id(hallId);
		hall.setHall_name(request.getParameter("hall_name"));
		hall.setUpper_limit(Integer.parseInt(request.getParameter("upper_limit")));
		hall.setRows(Integer.parseInt(request.getParameter("rows")));
		hall.setCols(Integer.parseInt(request.getParameter("cols")));
		hall.setHallseat_info(request.getParameter("hallseat_info"));

		int count = this.hallMapper.addNewHall(hall);
		System.out.println("执行插入影厅成功 ? : " + count);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/getMyHallInfoAll")
	@ResponseBody
	public List<Map<String, Object>> getMyHallInfoAll(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");

		System.out.println("开始遍历影厅信息  ： " + theatre_id);

		List<Hall> halls = this.hallMapper.getMyHallInfoAll(theatre_id);
		Map<String, Object> map = null;
		List<Map<String, Object>> myHallInfo = new ArrayList<>();
		Iterator<Hall> itHall = halls.iterator();
		while (itHall.hasNext()) {
			Hall hall = (Hall) itHall.next();
			map = new HashMap<>();
			map.put("hall_id", hall.getHall_id());
			map.put("hall_name", hall.getHall_name());
			map.put("upper_limit", hall.getUpper_limit());
			map.put("rows", hall.getRows());
			map.put("cols", hall.getCols());
			map.put("hallseat_info", hall.getHallseat_info());
			myHallInfo.add(map);
		}
		System.out.println("影厅信息 ：" + myHallInfo);
		return myHallInfo;
	}

	@RequestMapping("/updateMyHall")
	@ResponseBody
	public boolean updateMyHall(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		hall.setTheatre_id((String) session.getAttribute("currentUser"));
		hall.setHall_id(request.getParameter("hall_id"));
		hall.setHall_name(request.getParameter("hall_name"));
		hall.setUpper_limit(Integer.parseInt(request.getParameter("upper_limit")));
		hall.setRows(Integer.parseInt(request.getParameter("rows")));
		hall.setCols(Integer.parseInt(request.getParameter("cols")));
		hall.setHallseat_info(request.getParameter("hallseat_info"));

		int count = this.hallMapper.updateMyHall(hall);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/deleteMyHall")
	@ResponseBody
	public boolean deleteMyHall(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		String theatre_id = (String) session.getAttribute("currentUser");
		String hall_id = request.getParameter("hall_id");

		int count1 = this.hallMapper.deleteMyHallWithBeon(theatre_id, hall_id);
		System.out.println("删除的上映详细信息数量 : " + count1);
		int count = this.hallMapper.deleteMyHall(theatre_id, hall_id);
		System.out.println("删除的影厅的数量 ： " + count);

		if ((count1 + count) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
