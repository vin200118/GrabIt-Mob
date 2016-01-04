package com.grabIt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grabIt.domain.User;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate; 
	
	
	@Override
	public User getLoginUserDetails(User user) {
		String SQL = "select id, username, password from user where username=:username AND password=:password";
		
		  MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		  namedParameters.addValue("username", user.getUsername());     
		  namedParameters.addValue("password", user.getPassword()); 
		  User currentUser = null;
		  try{
				  currentUser =  (User)namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new RowMapper<User>() {
			            public User mapRow(ResultSet result, int rowNum) throws SQLException {
			            	User user = new User();
			                user.setId(result.getString("id"));
			                user.setUsername(result.getString("userName"));
			                return user;
			            }
				  });
				  
		  }catch(EmptyResultDataAccessException e){
			  return null;
		  }
		return currentUser;
		
	}

}
