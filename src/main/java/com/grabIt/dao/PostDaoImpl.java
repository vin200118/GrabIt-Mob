package com.grabIt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.grabIt.domain.Post;
import com.grabIt.util.Utility;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private NamedParameterJdbcTemplate  namedParameterJdbcTemplate; 
	

	@Override
	public int addPost(Post post) {
		String SQL = "INSERT INTO \"post\" (id, user_id, title, category_id, subcategory_id, image_path, brand, model, type, price, item_condition, date_of_purchase, description, contact_name, contact_number, contact_email_id, created_date) "
								+ " VALUES (:id, :userId, :title, :categoryId, :subcategoryId, :imagePath, :brand, :model, :type, :price,:condition, :dateOfPurchase, :description, :contactName, :contactNumber, :contactEmailId, :createdDate)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", Utility.getUUID());
		namedParameters.addValue("userId", post.getUserId());
		namedParameters.addValue("title", post.getTitle());
		namedParameters.addValue("categoryId", post.getCategoryId());
		namedParameters.addValue("subcategoryId", post.getSubCategoryId());	
		namedParameters.addValue("imagePath", post.getImagePath());
		namedParameters.addValue("brand", post.getBrand());
		namedParameters.addValue("model", post.getModel());
		namedParameters.addValue("type", post.getType());
		namedParameters.addValue("price", post.getPrice());
		namedParameters.addValue("condition", post.getCondition());
		namedParameters.addValue("dateOfPurchase", Utility.getSqlTimeStampDate(post.getDateOfPurchase()));
		namedParameters.addValue("description", post.getDescription());
		namedParameters.addValue("contactName", post.getContactName());
		namedParameters.addValue("contactNumber", post.getContactNumber());
		namedParameters.addValue("contactEmailId", post.getContactEmail());
		namedParameters.addValue("createdDate", new Timestamp(new Date().getTime()));
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}
	
	@Override
	public List<Post> getPosts(String search, String categoryId, String subCategoryId, String userId) {
		boolean isAndCondition = false;
		String SQL = "SELECT id, user_id, title, category_id, subcategory_id, image_path, brand, model, type, price, item_condition, date_of_purchase, description, "
					+ "contact_name, contact_number, contact_email_id FROM \"post\"";
					if(StringUtils.isNotEmpty(search) || 
							(StringUtils.isNotEmpty(categoryId) && !"0".equals(categoryId)) || 
							(StringUtils.isNotEmpty(subCategoryId) && !"0".equals(subCategoryId)) ||
							(StringUtils.isNotEmpty(userId) && !"0".equals(subCategoryId)) ){
						SQL+=" WHERE";
					}			
					if(StringUtils.isNotEmpty(search)){
						SQL+=" title like '%"+search+"%'";
						isAndCondition = true;
					}
					if(StringUtils.isNotEmpty(categoryId) && !"0".equals(categoryId)){
						if(isAndCondition){
							SQL+=" OR";
						}
						if(StringUtils.isNotEmpty(subCategoryId) && !"0".equals(subCategoryId)){
							SQL+=" (";
						}
						SQL+=" category_id='"+categoryId+"'";
						isAndCondition = true;
					}
					if(StringUtils.isNotEmpty(subCategoryId) && !"0".equals(subCategoryId)){
						if(isAndCondition){
							SQL+=" AND";
						}
						SQL+=" subcategory_id='"+subCategoryId+"')";
						
					}
					if(StringUtils.isNotEmpty(userId) && !"0".equals(userId)){
						SQL+=" user_id='"+userId+"'";
					}
					SQL+= " order by created_date desc ;";
		List<Post> posts =  namedParameterJdbcTemplate.query(SQL,  new RowMapper<Post>() {
            public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Post post = new Post();
            	post.setBrand(rs.getString("brand"));
            	post.setCategoryId(rs.getString("category_id"));
            	post.setCondition(rs.getString("item_condition"));
            	post.setContactName(rs.getString("contact_name"));
            	post.setContactNumber(rs.getString("contact_number"));
            	post.setDateOfPurchase(rs.getString("date_of_purchase"));
            	post.setDescription(rs.getString("description"));
            	post.setContactEmail(rs.getString("contact_email_id"));
            	post.setId(rs.getString("id"));
            	post.setImagePath(rs.getString("image_path"));
            	post.setModel(rs.getString("model"));
            	post.setPrice(rs.getInt("price"));
            	post.setSubCategoryId(rs.getString("subcategory_id"));
            	post.setTitle(rs.getString("title"));
            	post.setType(rs.getString("type"));
            	post.setUserId(rs.getString("user_id"));
                return post;
            }
             
        });
		return posts;
		
	}
	
	
	@Override
	public Post getPost(String id) {
		String SQL = "SELECT id, user_id, title, category_id, subcategory_id, image_path, brand, model, type, price, item_condition, date_of_purchase, description, "
				+ "contact_name, contact_number, contact_email_id FROM \"post\" WHERE id=:id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", id); 
		Post post = (Post) namedParameterJdbcTemplate.queryForObject(SQL, namedParameters,  new RowMapper<Post>() {
 
            public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Post post = new Post();
            	post.setBrand(rs.getString("brand"));
            	post.setCategoryId(rs.getString("category_id"));
            	post.setCondition(rs.getString("item_condition"));
            	post.setContactName(rs.getString("contact_name"));
            	post.setContactNumber(rs.getString("contact_number"));
            	post.setDateOfPurchase(rs.getString("date_of_purchase"));
            	post.setDescription(rs.getString("description"));
            	post.setContactEmail(rs.getString("contact_email_id"));
            	post.setId(rs.getString("id"));
            	post.setImagePath(rs.getString("image_path"));
            	post.setModel(rs.getString("model"));
            	post.setPrice(rs.getInt("price"));
            	post.setSubCategoryId(rs.getString("subcategory_id"));
            	post.setTitle(rs.getString("title"));
            	post.setType(rs.getString("type"));
            	post.setUserId(rs.getString("user_id"));
                return post;
            
            }
             
        });
		return post;
		
	}
	
	

}
