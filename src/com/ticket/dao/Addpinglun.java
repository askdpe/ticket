package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ticket.entity.remark;


@Component("addpinglun")
public interface Addpinglun {
public void Addmypinglun(remark remark);
public List<remark> SearchPinglunofflim(remark remark);
}
