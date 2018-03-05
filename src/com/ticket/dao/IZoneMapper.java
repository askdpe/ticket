package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.Zone;

@Component("zoneMapper")
public interface IZoneMapper {
	List<Zone> getZoneInfoAll();
}
