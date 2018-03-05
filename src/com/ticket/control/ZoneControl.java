package com.ticket.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticket.entity.Zone;
import com.ticket.dao.IZoneMapper;

@Controller
@RequestMapping("/zone")
public class ZoneControl {
	IZoneMapper zoneMapper;
	Zone zone = new Zone();

	public IZoneMapper getZoneMapper() {
		return zoneMapper;
	}

	@Autowired
	public void setZoneMapper(@Qualifier("zoneMapper") IZoneMapper zoneMapper) {
		this.zoneMapper = zoneMapper;
	}

	@RequestMapping("/getZoneInfoAll")
	@ResponseBody
	public List<Map<String, Object>> getZoneInfoAll(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		List<Zone> zones = this.zoneMapper.getZoneInfoAll();
		Map<String, Object> map = null;
		List<Map<String, Object>> zoneInfos = new ArrayList<>();
		Iterator<Zone> itZone = zones.iterator();
		while (itZone.hasNext()) {
			zone = itZone.next();
			map = new HashMap<>();
			map.put("zone_id", zone.getZone_id());
			map.put("zone_name", zone.getZone_name());
			map.put("city_id", zone.getCity_id());
			zoneInfos.add(map);
		}
		return zoneInfos;
	}
}
