package com.grabIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.grabIt.dao.EMailDao;

@Service("emailService")
public class EmailService {
	
	@Autowired
	private JavaMailSender  emailService; // MailSender interface defines a strategy
										// for sending simple mails
	
	@Autowired
	private EMailDao eMailDao;
 
	public void crunchifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
 
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(fromAddress);
		msg.setTo(toAddress);
		msg.setSubject(subject);
		msg.setText(msgBody);
		emailService.send(msg);
	}
	
	public boolean isEmailIdExist(String emailId){
		return eMailDao.isEmailIdExist(emailId);
	}
	
	

}
