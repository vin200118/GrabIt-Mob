package com.grabIt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.domain.Category;
import com.grabIt.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping(value="/category")
public class CategoriesController extends BaseController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories(){
		return new ResponseEntity<List<Category>>(categoryService.getCategories(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getSubCategories(@PathVariable("id") String categoryId){
		return new ResponseEntity<List<Category>>(categoryService.getSubCategory(categoryId),HttpStatus.OK);
	}
	

}
