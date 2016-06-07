package com.grabIt.dao;

import com.grabIt.domain.User;

public interface UserDao {

	boolean isUserExist(String username, String password);

	int updateUsersPassword(String username, String password);

	User getUserDetails(String username);

}
