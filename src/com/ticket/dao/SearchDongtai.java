package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.dongtai;
@Component("searchdongtai")
public interface SearchDongtai {
public List<dongtai> Getmydongtai(String uname);
}
