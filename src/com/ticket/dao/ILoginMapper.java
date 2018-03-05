package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.Login;

@Component("loginMapper")
public interface ILoginMapper {
	List<Login> isLoginer(String login_name, String login_pwd);

	int recordLoginTime(String login_name);
}
