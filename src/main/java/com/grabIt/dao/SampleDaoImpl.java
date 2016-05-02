package com.grabIt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.grabIt.domain.Sample;

@Repository
public class SampleDaoImpl implements SampleDao{

	@Autowired
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate; 
	
	@Override
	public Sample getSampleData(String id) {
		String SQL = "select id,name,lastName from \"sample\" where id=:id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id); 
		Sample sample = (Sample) namedParameterJdbcTemplate.queryForObject(SQL, namedParameters,  new RowMapper<Sample>() {
 
            public Sample mapRow(ResultSet result, int rowNum) throws SQLException {
            	Sample contact = new Sample();
                contact.setName(result.getString("name"));
                contact.setLastName(result.getString("lastName"));
                contact.setId(result.getString("id"));
                 
                return contact;
            }
             
        });
		return sample;
		
	}

	@Override
	public List<Sample> getListSample() {
		String SQL = "select id,name,lastName from \"sample\"";
		List<Sample> sample =  namedParameterJdbcTemplate.query(SQL,  new RowMapper<Sample>() {
 
            public Sample mapRow(ResultSet result, int rowNum) throws SQLException {
            	Sample contact = new Sample();
                contact.setName(result.getString("name"));
                contact.setLastName(result.getString("lastName"));
                contact.setId(result.getString("id"));
                 
                return contact;
            }
             
        });
		return sample;
		
	}

	@Override
	public void addSample(Sample sample) {
		 String SQL = "INSERT INTO \"Sample\" (id, name, lastName) VALUES (:id, :name, :lastName)";  
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource(); 
		  namedParameters.addValue("id", sample.getId());     
		  namedParameters.addValue("name", sample.getName());     
		  namedParameters.addValue("lastName", sample.getLastName()); 
	      namedParameterJdbcTemplate.update(SQL, namedParameters);  
	}

	@Override
	public void updateSample(Sample sample) {
		String SQL = "UPDATE \"Sample\" SET NAME=:name,lastName=:lastName where id=:id";  
		  MapSqlParameterSource namedParameters = new MapSqlParameterSource();  
		  namedParameters.addValue("name", sample.getName());     
		  namedParameters.addValue("lastName", sample.getLastName());  
	      namedParameters.addValue("id", sample.getId());  
	      namedParameterJdbcTemplate.update(SQL, namedParameters);  
	}

	@Override
	public void deleteSampleData(int id) {
		String SQL = "DELETE FROM \"Sample\" where id=:id"; 
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
	      namedParameterJdbcTemplate.update(SQL, namedParameters);  
	}


}
