package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.dingdan;
@Component("searchdingdan")
public interface SearchDingdan {
	public int GetMaxPageNumOfAll(dingdan dingdan);
	public List<dingdan> GetMyDingdaninfo(dingdan dingdan);
	public int GetMaxPageNumOfSaveAll(dingdan dingdan);
	public List<dingdan> GetSaveDingdaninfo(dingdan dingdan);
}
