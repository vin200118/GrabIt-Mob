package com.grabIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabIt.dao.UserDao;
import com.grabIt.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User getLoginUserDetails(User user) {
		return userDao.getLoginUserDetails(user);
	}

}
