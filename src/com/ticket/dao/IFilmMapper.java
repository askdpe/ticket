package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.Beon;
import com.ticket.entity.Film;

@Component("filmMapper")
public interface IFilmMapper {
	int addNewFilm(Film film);

	List<Film> getFilmInfoAll();

	List<Film> getFilmInfoNotInMyBeon(String theatre_id);

	List<Film> isExistTheFilm(Film film);

	int addNewBeon(Beon beon);

	List<Beon> showMyBeon(String theatre_id);

	List<Beon> getMyBeonHistory(String theatre_id);

	int updateMyBeon(Beon beon);

	int deleteMyBeon(String film_id, String theatre_id);

	int deleteMyBeoff(String theatre_id);
}
