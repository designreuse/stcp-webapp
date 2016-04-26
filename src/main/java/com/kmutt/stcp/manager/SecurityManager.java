package com.kmutt.stcp.manager;


import java.io.IOException;
import java.security.GeneralSecurityException;
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
import com.kmutt.stcp.repository.RoleUserRepository;
import com.kmutt.stcp.repository.UserRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kmutt.stcp.manager.SentMailManager;

@Component("securityManager")
public class SecurityManager {
	// Field//
    private final Logger logger = LoggerFactory.getLogger(SecurityManager.class);
    
    @Autowired
    private PasswordManager passwordManager;
    
    @Autowired
    private SentMailManager sentmailManager;
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleUserRepository roleUserRepository;
    
    
    
    public SecurityManager(){
    }
    
    public String ValidateBeforeSentEmail(String Email){
    	String result = "";
    	
    	try {
    		if(isValidEmail(Email) == false){
        		result = "Email Invalid format";
        	}
    		// Is Not Exist in table User ; return "Email is not KMUTT Email Account" 
    		else if(isExistEmailInUser(Email) == false){
    			result = "Email is not KMUTT Email Account";
    		}
    		// Is Exist in table Account ; return "User is exist ,Please Login"
    		else if(isExistEmailInAccount(Email) == true){
    			result = "User is exist ,Please Login";
    		}
		} catch (Exception e) {
			result = e.getMessage();
		}
    	
    	return result;
    }
    
    public String RegisterConfirm(String token){
    	String Email = "";
    	
    	//TODO ; check Emai
    	
    	
    	return "";
    }
    
    public List<User> TestSQL(String tt){
    	/*AccountRepository testAccountRepo = new AccountRepository();
    	
    	
    	return test;*/
    	
    	/*User user = new User();
        user.setId(3);
        user.setCitizenId("11");
        user.setEmail("email");
        user.setFaculty("faculty");
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.setMajor("major");
        user.setSemester(1);
        userRepository.create(user);*/

       /* RoleUser role = new RoleUser();
        role.setId(3);
        roleUserRepository.create(role);*/

      /*  Account account = new Account();
        account.setUsername("username");
        account.setPassword("password");
        account.setUser(userRepository.findAll().get(1));
        account.setRoleUser(roleUserRepository.findAll().get(1));
        accountRepository.create(account);*/
        
        List<User> list = userRepository.findAll();
        return list;
    }
    
    public void sendMail(String to){
    	sentmailManager.SentMail(to);
    }

    public String ValidatePasswordBeforeCreateUser(String Password){
    	String result = "";
    	
    	try {
			if(Password.length() < 8){
				result = "Password must have length more than 8 character";
			}
		} catch (Exception e) {
			result = e.getMessage();
		}
    	
    	return result;
    }
    
    public String CreateUser(String Email,String Password){
    	String result = "";
    			
    	try {
			// Get user id
    		//String hqlUser = "from user where email = '" + Email + "'";
    		//User usr = (User)userRepository.queryHQL(hqlUser).get(0);
    		User usr = findUserByEmail(Email);
    		
    		if(usr.equals(null) == true){
    			return "Email does not KMUTT Email Account";
    		}
    		
    		// Get Role User
    		//String hqlRole = "from role_user where id = " + usr.getId().toString();
    		//RoleUser role = (RoleUser)roleUserRepository.queryHQL(hqlRole);
    		RoleUser role = findRoleUserByUserID(usr.getId());
    		
    		if(role.equals(null)){
    			return "User don't have Role";
    		}
    		
    		Account newAccount = new Account();
    		newAccount.setUsername(Email);
    		newAccount.setPassword(passwordManager.encrypt(Password));
    		newAccount.setUser(usr);
    		newAccount.setRoleUser(role);
    		
    		accountRepository.create(newAccount);
		} catch (Exception e) {
			result = e.getMessage();
		}
    	
    	return result;
    }
    
    public String Login(String UserName,String Password){
    	String result = "";
		
    	try {
			// Get Account from table Account by UserName
    		Account acc = findAccountByUserName(UserName);
    		
    		if(acc.equals(null)){
    			result ="User or Password does not correct.";
    		}
    		else{
    			String encryptPassword = passwordManager.encrypt(Password);
    			String databasePassword = acc.getPassword();
    			
    			if(encryptPassword.equals(databasePassword) == false){
    				result = "User or Password does not correct.";
    			}
    		}
		} catch (Exception e) {
			result = e.getMessage();
		}
    	
    	return result;
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
    	// IsExist In table Account ; return true
    	// IsNotExist In table Account ; return false
    	
    	Boolean result = false;
    	
    	try {
    		List<Account> allAccountList = accountRepository.findAll();
    		
    		if(allAccountList.isEmpty() == false){
    			for (Account acc : allAccountList) {
					if(acc.getUsername().equalsIgnoreCase(Email)){
						result = true;
						break;
					}
				}
    		}	
		} catch (Exception e) {
			throw e;
		}
    	
    	return result;
    }
    
    private Boolean isExistEmailInUser(String Email){
    	// IsExist in table User ; return true
    	// IsNotExist in table User ; return false
    	
    	Boolean result = false;
    	
    	try {
    		List<User> allUserList = userRepository.findAll();
    		
    		if(allUserList.isEmpty() == false){
    			for (User usr : allUserList) {
					if(usr.getEmail().equalsIgnoreCase(Email)){
						result = true;
						break;
					}
				}
    		}
		} catch (Exception e) {
			throw e;
		}
    	
    	return result;
    }
        
    private User findUserByEmail(String textEmail){
    	List<User> userList = userRepository.findAll();
    	
    	for (User user : userList) {
			if(user.getEmail().equalsIgnoreCase(textEmail)){
				return user;
			}
		}
    	
    	return null;
    }
    
    private RoleUser findRoleUserByUserID(Integer UserID){
    	List<RoleUser> roleuserList = roleUserRepository.findAll();
    	
    	for (RoleUser roleUser : roleuserList) {
			if(roleUser.getId() == UserID){
				return roleUser;
			}
		}
    	
    	return null;
    }

    private Account findAccountByUserName(String UserName){
    	List<Account> accountList = accountRepository.findAll();
    	
    	for (Account account : accountList) {
			if(account.getUsername().equals(UserName)){
				return account;
			}
		}
    	
    	return null;
    }
}
