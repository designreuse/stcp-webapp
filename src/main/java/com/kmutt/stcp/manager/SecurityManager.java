package com.kmutt.stcp.manager;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpSession;

import com.kmutt.stcp.service.CoursePlannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.repository.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kmutt.stcp.manager.SentMailManager;

import com.kmutt.stcp.repository.AccountRepository;

@Component("securityManager")
public class SecurityManager {
	// Field//
    private final Logger logger = LoggerFactory.getLogger(SecurityManager.class);
    
    @Autowired
    private SentMailManager sentmailManager;
    
    @Autowired
    private AccountRepository accountRepository;
    
    public SecurityManager(){
    }
    
    public SecurityManager(HttpSession session){
    	sentmailManager = this.getCurrentSentMailManager(session);
    	accountRepository = this.getCurrentAccountRepository(session);
    	
    }
    
    public String ValidateBeforeSentEmail(String Email){
    	String result = "";
    	
    	if(isValidEmail(Email) == false){
    		result = "Email Invalid format";
    	}
    	else if(isExistEmailInUser(Email) == true){
    		result = "User is exist ,Please Login";
    	}
    	else if(isExistEmailInAccount(Email) == true){
    		result = "Email is not KMUTT Email Account";
    	}
    	
    	return result;
    }
    
    public String RegisterConfirm(String token){
    	String Email = "";
    	
    	//TODO ; check Emai
    	
    	
    	return "";
    }
    
    public List<Account> TestSQL(String tt){
    	List<Account> test = accountRepository.querySQL("SELECT * FROM Account");
    	
    	return test;
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
    
 	@SuppressWarnings("finally")
	private AccountRepository getCurrentAccountRepository(HttpSession session) {

 		AccountRepository _accountRepo = null;

 		try {

 			_accountRepo = (AccountRepository) session.getAttribute("accountRepo");

 			if (_accountRepo == null) {
 				_accountRepo = new AccountRepository();
 			}

 		} catch (Exception e) {

 			logger.error(e.getMessage());

 			_accountRepo = new AccountRepository();

 		} finally {

 			session.setAttribute("accountRepo", _accountRepo);
 			return _accountRepo;

 		}

 	}
    
    private Boolean isValidEmail(String Email){
    	Boolean result = true;
    	
    	try {
    		InternetAddress emailAddr = new InternetAddress(Email);
    		emailAddr.validate();
		} catch (AddressException  e) {
			result = false;
		}
    	
    	return result;
    }
    
    private Boolean isExistEmailInAccount(String Email){
    	Boolean result = false;
    	
    	try {
			//TODO wait connect db
		} catch (Exception e) {
			
		}
    	
    	return result;
    }
    
    private Boolean isExistEmailInUser(String Email){
    	Boolean result = false;
    	
    	try {
			//TODO wait connect db
		} catch (Exception e) {
			
		}
    	
    	return result;
    }
}
