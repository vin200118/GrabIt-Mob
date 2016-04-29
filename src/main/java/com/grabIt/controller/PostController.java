package com.grabIt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.domain.Post;
import com.grabIt.service.PostService;
import com.grabIt.validator.PostValidator;


@CrossOrigin
@RestController
@RequestMapping(value="/post")
public class PostController extends BaseController {
	
	@Autowired
	private PostService postService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new PostValidator());
	}
	
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<?>  addSample(@Valid @RequestBody Post post, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.FORBIDDEN);
	    }
		postService.addSample(post);
		setMessage(POST_CREATED);
		setStatusCode(STATUS_CODE_CREATED);
        return new ResponseEntity<BaseController>(this, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false,value ="search") String search,
											   @RequestParam(required = false,value ="categoryId") String categoryId,
											   @RequestParam(required = false,value ="subCategoryId") String subCategoryId){
		
		return new ResponseEntity<List<Post>>(postService.getPosts(search, categoryId, subCategoryId),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> getPosts(@PathVariable("id") String id){
		
		return new ResponseEntity<Post>(postService.getPost(id),HttpStatus.OK);
	}
	

}
