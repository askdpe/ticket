package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.Hall;

@Component("hallMapper")
public interface IHallMapper {
	int addNewHall(Hall hall);

	List<Hall> getMyHallInfoAll(String theatre_id);

	int updateMyHall(Hall hall);

	int deleteMyHall(String theatre_id, String hall_id);

	int deleteMyHallWithBeon(String theatre_id, String hall_id);
}
