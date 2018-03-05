package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.Theatre;

@Component("theatreMapper")
public interface ITheatreMapper {
	int signUpForTheatre(Theatre theatre);

	List<Theatre> isExistTheTheatre(Theatre theatre);

	List<Theatre> getMyTheatreInfoAll(String theatre_id);

	List<Theatre> getTheatreInfoAll();

	int updateTheTheatreStatust(String theatre_status, String theatre_id);

	int putTheatreIntoLogin(Theatre theatre);
}
