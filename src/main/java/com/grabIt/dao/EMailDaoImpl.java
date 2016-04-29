package com.grabIt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EMailDaoImpl implements EMailDao {

	@Autowired
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate; 
	
	@Override
	public boolean isEmailIdExist(String emailId) {
		String SQL = "select username from \"user\" where username=:emailId";
		
		  MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		  namedParameters.addValue("emailId", emailId);     
		  boolean isEmailIdExist = false;
		  try{
			  isEmailIdExist =  (Boolean)namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new RowMapper<Boolean>() {
			            public Boolean mapRow(ResultSet result, int rowNum) throws SQLException {
			            	return true;
			            }
				  });
				  
		  }catch(EmptyResultDataAccessException e){
			  return false;
		  }
		return isEmailIdExist;
		
	}

}
