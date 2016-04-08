package com.grabIt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grabIt.domain.Category;

@Repository
public class CategoryDaoImpl  implements CategoryDao {

	@Autowired
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate; 
	
	@Override
	public List<Category> getCategories() {
		String SQL = "SELECT id, name FROM category";
		List<Category> categories =  namedParameterJdbcTemplate.query(SQL,  new RowMapper<Category>() {
 
            public Category mapRow(ResultSet result, int rowNum) throws SQLException {
            	Category category = new Category();
            	category.setName(result.getString("name"));
            	category.setId(result.getString("id"));
                 
                return category;
            }
             
        });
		return categories;
	}

	@Override
	public List<Category> getSubCategories(String categoryId) {
		String SQL = "SELECT id, name, category_id FROM sub_category WHERE category_id=:categoryId";
		
		 MapSqlParameterSource namedParameters = new MapSqlParameterSource();  
		  namedParameters.addValue("categoryId", categoryId); 
		List<Category> subCategoies =  namedParameterJdbcTemplate.query(SQL,namedParameters,  new RowMapper<Category>() {
 
            public Category mapRow(ResultSet result, int rowNum) throws SQLException {
            	Category category = new Category();
            	category.setName(result.getString("name"));
            	category.setId(result.getString("id"));
            	category.setCategoryId(result.getString("category_id"));
                 
                return category;
            }
             
        });
		return subCategoies;
	}

}
