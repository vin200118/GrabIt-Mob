package com.grabIt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.dao.UserDao;
import com.grabIt.domain.User;
import com.grabIt.service.UserService;
import com.grabIt.validator.UserValidator;

@CrossOrigin
@RestController
@RequestMapping(value="/user/")
public class UserController extends BaseController {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	public UserService userService;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new UserValidator(userService,userDao));
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public ResponseEntity<BaseController>  getLoginUserDetails(@Valid @RequestBody User user , BindingResult result){
		
		if(!result.hasErrors()){
			User userdetails = userService.getUserDetails(user.getUsername());
			getDetails().put("id", userdetails.getId());
			setMessage(LOGIN_SUCESS);
			setStatusCode(STATUS_CODE_OK);
		}else{
			setMessage(result.getFieldErrors().get(0).getDefaultMessage());
			setStatusCode(STATUS_CODE_AUTH);
		}
		return new ResponseEntity<BaseController>(this,HttpStatus.OK);
	}
}
