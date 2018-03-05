package com.ticket.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ticket.entity.Users;

@Component("userMapper")
public interface IUsersMapper {
	public List<Users> SelectUsersAll();
    public int AddUser(Users user);
    public int DeleteUsers(Users user);
    public int CheckUserName(Users user);
    public int Login(Users user);
    public int UpdateLoginTime(Users user);
    public int UpdatePassword(Users user);
    public int UpdateUserInfo(Users user);
    public int UpdateUserPicture(Users user);
    public List<Users> SelectUserinfoByUname(Users user);
    public int CountUsers();
}
