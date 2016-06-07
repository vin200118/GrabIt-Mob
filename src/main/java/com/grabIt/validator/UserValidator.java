package com.grabIt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.grabIt.dao.UserDao;
import com.grabIt.domain.User;
import com.grabIt.service.UserService;

@Component
public class UserValidator implements Validator {

	private UserService userService;
	
	public UserValidator(){
		
	}
	
	public  UserValidator(UserService userService, UserDao userDao) {
		this.userService = userService;
		this.userService.setUserDao(userDao);
	}
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        
       User user = (User) target;
        //perform additional checks
        //if name already exists or ?
        boolean isUserExist = userService.isUserExist(user.getUsername(),user.getPassword());
        if(!isUserExist){
        	 e.rejectValue("password", "Username and Password does not exists.");
        }
       
    }
}