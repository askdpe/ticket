package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.BeonDetail;

@Component("beonDetailMapper")
public interface IBeonDetailMapper {
	int addMyBeonDetail(BeonDetail beonDetail);

	List<BeonDetail> getMyBeonDetail(String theatre_id);
}
