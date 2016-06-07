package com.grabIt.dao;

import java.util.List;

import com.grabIt.domain.Category;

public interface CategoryDao {
	
	List<Category> getCategories();
	List<Category> getSubCategories(String categoryId);

}
