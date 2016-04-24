package com.kmutt.stcp.manager;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import com.kmutt.stcp.service.CoursePlannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.repository.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kmutt.stcp.manager.*;

@Component("securityManager")
public class SecurityManager {
	// Field//
    private final Logger logger = LoggerFactory.getLogger(SecurityManager.class);
    
    @Autowired
    private SentMailManager sentmailManager;
    
    public SecurityManager(){
    }
    
    public SecurityManager(HttpSession session){
    	sentmailManager = this.getCurrentSentMailManager(session);
    }
    
    public void sendMail(String to){
    	sentmailManager.SentMail(to);
    }
    
 // Method//
 	@SuppressWarnings("finally")
 	private SentMailManager getCurrentSentMailManager(HttpSession session) {

 		SentMailManager _sendMailManage = null;

 		try {

 			_sendMailManage = (SentMailManager) session.getAttribute("sendMailMng");

 			if (_sendMailManage == null) {
 				//TODO: change session name to get account
 				_sendMailManage = new SentMailManager();
 			}

 		} catch (Exception e) {

 			logger.error(e.getMessage());

 			_sendMailManage = new SentMailManager();

 		} finally {

 			session.setAttribute("sendMailMng", _sendMailManage);
 			return _sendMailManage;

 		}

 	}
    
    
   /* private MailSender mailSender;
    
    public SecurityManager() {}
    
    public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}*/
    
}
