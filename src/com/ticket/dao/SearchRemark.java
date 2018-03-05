package com.ticket.dao;

import java.util.List;
import org.springframework.stereotype.Component;

import com.ticket.entity.remark;

@Component("searchremark")
public interface SearchRemark {
public int GetMaxPageNumOfAll(remark remarks);
public List<remark> GetRemarkinfo(remark remark);
}
