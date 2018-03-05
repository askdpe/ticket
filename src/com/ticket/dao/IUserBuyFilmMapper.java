package com.ticket.dao;

import java.util.List;
import org.springframework.stereotype.Component;

import com.ticket.entity.BeonDetail;
import com.ticket.entity.Film;
import com.ticket.entity.FilmTicket;
import com.ticket.entity.Hall;
import com.ticket.entity.Theatre;


@Component("userBuyFilmMapper")
public interface IUserBuyFilmMapper {
	public List<Film> SelectNewFilmOrder(); 
	public List<Film> SelectHotFilmOrder();
	public List<BeonDetail> SelectAllBeonTime(String filmName); 
	public List<BeonDetail> SelectBeonDetailByTime(BeonDetail beonDetail);
	public String SelectHallSeatinfo(String theatre_name,String hall_name);
	public String SelectBeonSeatinfo(BeonDetail beonDetail); 
	public List<Film> SelectFilmInfo(String film_name); 
	public int UpdateLockSeat(BeonDetail beonDetail);
	public int AddWaitPay(FilmTicket filmTicket);
	public int UpdateFilmPayInfo(FilmTicket filmTicket);
	public FilmTicket SelectFilmPayInfo(FilmTicket filmTicket);
	public int DeleteFilmPayInfo(String ticket_id);
	public BeonDetail SelectBeonSeatinfoByTicketId(String ticket_id);
	
}
