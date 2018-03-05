package com.ticket.control;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.entity.BeonDetail;
import com.ticket.dao.IBeonDetailMapper;

@Controller
@RequestMapping("/beonDetail")
public class BeonDetailControl {
	IBeonDetailMapper beonDetailMapper;
	BeonDetail beonDetail = new BeonDetail();

	public IBeonDetailMapper getBeonDetailMapper() {
		return beonDetailMapper;
	}

	@Autowired
	public void setBeonDetailMapper(@Qualifier("beonDetailMapper") IBeonDetailMapper beonDetailMapper) {
		this.beonDetailMapper = beonDetailMapper;
	}

	@RequestMapping("/addMyBeonDetail")
	@ResponseBody
	public boolean addMyBeonDetail(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		beonDetail.setTheatre_id(theatre_id);
		beonDetail.setFilm_id(request.getParameter("film_id"));
		beonDetail.setHall_id(request.getParameter("hall_id"));
		beonDetail.setBeon_time(request.getParameter("beon_time"));
		beonDetail.setVersion(request.getParameter("version"));
		beonDetail.setBeon_seatnum(Integer.parseInt(request.getParameter("beon_seatnum")));
		beonDetail.setBeon_seatinfo(request.getParameter("beon_seatinfo"));
		beonDetail.setPrice(Double.parseDouble(request.getParameter("price")));

		int count = this.beonDetailMapper.addMyBeonDetail(beonDetail);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/showMyBeonDetail")
	@ResponseBody
	public List<Map<String, Object>> showMyBeonDetail(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		String theatre_id = (String) session.getAttribute("currentUser");
		List<BeonDetail> beonDetails = this.beonDetailMapper.getMyBeonDetail(theatre_id);
		Map<String, Object> map = null;
		List<Map<String, Object>> myBeonDetails = new ArrayList<>();
		Iterator<BeonDetail> itBeonDetail = beonDetails.iterator();
		while (itBeonDetail.hasNext()) {
			BeonDetail beonDetail = (BeonDetail) itBeonDetail.next();
			map = new HashMap<>();
			map.put("hall_id", beonDetail.getHall_id());
			map.put("film_id", beonDetail.getFilm_id());
			map.put("beon_time", beonDetail.getBeon_time());
			map.put("version", beonDetail.getVersion());
			map.put("beon_seatnum", beonDetail.getBeon_seatnum());
			map.put("beon_seatinfo", beonDetail.getBeon_seatinfo());
			map.put("price", beonDetail.getPrice());
			map.put("hall_name", beonDetail.getHall().getHall_name());
			map.put("film_name", beonDetail.getFilm().getFilm_name());
			myBeonDetails.add(map);
		}
		return myBeonDetails;
	}
}
