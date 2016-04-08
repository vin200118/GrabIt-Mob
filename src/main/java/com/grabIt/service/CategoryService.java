package com.grabIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabIt.dao.CategoryDao;
import com.grabIt.domain.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> getCategories(){
		
		return categoryDao.getCategories();
	}
	
    public List<Category> getSubCategory(String categoryId){
		
		return categoryDao.getSubCategories(categoryId);
	}
	
	
	

}
