package com.grabIt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.domain.Sample;
import com.grabIt.domain.User;
import com.grabIt.service.UserService;


@RestController
@RequestMapping(value="/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public ResponseEntity<User>  getLoginUserDetails(@RequestBody User user){
		
		User loggedInUser = userService.getLoginUserDetails(user);
		
		if(loggedInUser != null){
			return new ResponseEntity<User>(loggedInUser, HttpStatus.FOUND);
		}
		return new ResponseEntity<User>(loggedInUser, HttpStatus.NOT_FOUND);
	}

}
