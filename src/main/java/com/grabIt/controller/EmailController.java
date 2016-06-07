package com.grabIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grabIt.domain.User;
import com.grabIt.service.EmailService;
import com.grabIt.service.UserService;
import com.grabIt.util.Utility;

@CrossOrigin
@RestController
@RequestMapping(value="/email/")
public class EmailController extends BaseController {
	
	@Value("${email.fromAddress}")
	private String fromAddress;
			
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "forgot-password",method = RequestMethod.GET)
	public ResponseEntity<BaseController>  sendLoginCredentialsThroughMail(@RequestParam String toAddress){
		
		if(!emailService.isEmailIdExist(toAddress)){
			setMessage("Email id is not present in System. Please contact to Administration.");
			setStatusCode(STATUS_CODE_NOT_FOUND);
			return new ResponseEntity<BaseController>(this,HttpStatus.OK);
		}
		userService.updateUsersPassword(toAddress, Utility.getRandomAlphNumberic());
		User user = userService.getUserDetails(toAddress);
		String userDetails = "UserName : "+user.getUsername()+
				" <BR>Password : "+user.getPassword();
		emailService.crunchifyReadyToSendEmail(toAddress, fromAddress, "Sent username and password to login in GrabIt.", userDetails);
		this.setMessage("Username and password sent to your email id");
		this.setStatusCode(STATUS_CODE_OK);
		return new ResponseEntity<BaseController>(this,HttpStatus.OK);
	}

}
