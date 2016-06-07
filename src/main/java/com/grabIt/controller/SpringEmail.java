package com.grabIt.controller;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.grabIt.service.EmailService;

public class SpringEmail {
	
	public static void main(String args[]) {
		 
		// Spring Bean file you specified in /src/main/resources folder
		String crunchifyConfFile = "rest-servlet.xml";
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(crunchifyConfFile);
 
		// @Service("crunchifyEmail") <-- same annotation you specified in CrunchifyEmailAPI.java
		EmailService crunchifyEmailAPI = (EmailService) context.getBean("crunchifyEmail");
		String toAddr = "padekarvinayak5@gmail.com";
		String fromAddr = "padekarvinayak5@gmail.com";
 
		// email subject
		String subject = "Hey.. This email sent by Crunchify's Spring MVC Tutorial";
 
		// email body
		String body = "There you go.. You got an email.. Let's understand details on how Spring MVC works -- By Crunchify Admin";
		crunchifyEmailAPI.crunchifyReadyToSendEmail(toAddr, fromAddr, subject, body);
	}

}
