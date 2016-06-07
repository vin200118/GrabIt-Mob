package com.grabIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabIt.dao.UserDao;
import com.grabIt.domain.User;

import lombok.Setter;

@Service
public class UserService {
	
	@Autowired
	@Setter
	private UserDao userDao;
	
	public boolean isUserExist(String username, String password) {
		return userDao.isUserExist(username, password);
	}
	
	public int updateUsersPassword(String username, String password) {
		return userDao.updateUsersPassword(username, password);
	}
	
	public User getUserDetails(String username){
		return userDao.getUserDetails(username);
	}

}
