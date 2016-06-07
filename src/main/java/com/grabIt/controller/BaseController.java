package com.grabIt.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseController {

	public static final String LOGIN_SUCESS = "Your are logged in sucessfully.";
	public static final String POST_CREATED = "Post created sucessfully.";
	public static final String LOGIN_FAIL = "Your username and password are incorrect.";
	public static final String STATUS_CODE_OK =  "200";
	public static final String STATUS_CODE_CREATED = "201";
	public static final String STATUS_CODE_AUTH = "401";
	public static final String STATUS_CODE_NOT_FOUND =  "400";
	
	private String statusCode;
	private String message;
	private Map<String, String> details = new HashMap<String, String>();
	
	
}
