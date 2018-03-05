package com.ticket.dao;

import org.springframework.stereotype.Component;

import com.ticket.entity.friend;
import com.ticket.entity.message;
@Component("addmessage")
public interface AddMessage {
public void addmessage(message messages);
public void addnewfriend(friend friend );
public int pdnewfriend(friend friend);
}
