package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.message;
import com.ticket.entity.userinfo;

@Component("searchmessage")
public interface SearchMessage {
	public List<message> GetMessageinfo(message message);
	public List<userinfo> GetMyfriend(userinfo userinfo);
	public List<userinfo> addsearchfriend(userinfo userinfo);
	public List<userinfo> searchfriend(userinfo userinfo);
}
