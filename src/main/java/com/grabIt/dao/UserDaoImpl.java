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
	public boolean isUserExist(String username, String password) {
		String SQL = "select id, username, password from user where username=:username AND password=:password";
		
		  MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		  namedParameters.addValue("username", username);     
		  namedParameters.addValue("password", password); 
		  boolean isUserExist = false;
		  try{
			  isUserExist =  (Boolean)namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new RowMapper<Boolean>() {
			            public Boolean mapRow(ResultSet result, int rowNum) throws SQLException {
			            	return true;
			            }
				  });
				  
		  }catch(EmptyResultDataAccessException e){
			  return false;
		  }
		return isUserExist;
		
	}
	
	
	@Override
	public User getUserDetails(String username) {
		String SQL = "select id, username, password FROM USER WHERE USERNAME=:username";
		
		  MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		  namedParameters.addValue("username", username);     
		  User user = null;
		  try{
			  user =  namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new RowMapper<User>() {
			            public User mapRow(ResultSet result, int rowNum) throws SQLException {
			            	User user = new User();
			            	user.setId(result.getString("id"));
			            	user.setUsername(result.getString("username"));
			            	user.setPassword(result.getString("password"));
							return user;
			            }
				  });
				  
		  }catch(EmptyResultDataAccessException e){
			  return null;
		  }
		return user;
		
	}
	
	@Override
	public int updateUsersPassword(String username, String password) {
		 String SQL = "UPDATE USER SET PASSWORD=:password WHERE USERNAME=:username";  
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		  namedParameters.addValue("password", password);     
		  namedParameters.addValue("username", username); 
	      return namedParameterJdbcTemplate.update(SQL, namedParameters);  
	}
	

}
