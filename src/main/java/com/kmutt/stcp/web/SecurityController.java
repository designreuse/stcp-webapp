package com.kmutt.stcp.web;

import com.kmutt.stcp.manager.PasswordManager;
import com.kmutt.stcp.manager.SecurityManager;
import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.Curriculum;
import com.kmutt.stcp.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SecurityController {
	private final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private SecurityManager securityManager;
	
	@RequestMapping(value = { "/", "/index" } , method = RequestMethod.GET)
	public String index(HttpSession session, Map<String, Object> model) {
		session.removeAttribute("loginAccount");
		
		logger.debug("index() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "index";
	}
	
	// show screen for input email
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String Register(Map<String, Object> model) {

		logger.debug("Register() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "RegisterUser";
	}
	
	// call function to validate email and sent mail
	@RequestMapping(value = { "/RegisterUser" }, method = RequestMethod.GET)
	@ResponseBody 
	public String RegisterUser(HttpSession session, @RequestParam("Email") String textEmail) {
		String msg = "";
		
		try {
			
			String validateResult = securityManager.ValidateEmail(textEmail);
			
			if(validateResult.isEmpty()){
				securityManager.SendMail(textEmail);
				msg = "success";
			}
			else{
				msg = validateResult;
			}
		} catch (Exception e) {
			logger.error("Method:RegisterUser|Err:" + e.getMessage());
			msg = e.getMessage();
		}

		String res = "{\"msg\":\"" + msg + "\"}";
		
		return res;
	}
	
	// show screen registration complete
	@RequestMapping(value = "/RegistrationComplete", method = RequestMethod.GET)
	public String RegistrationComplete(Map<String, Object> model) {

		logger.debug("RegistrationComplete() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "RegistrationComplete";
	}
	
	// show screen from click link in email and for input password
	@RequestMapping(value = "/RegitrationConfirm", method = RequestMethod.GET)
	public String RegitrationConfirm(Map<String, Object> model,@RequestParam("token") String textToken) {

		logger.debug("RegitrationConfirm() is executed!");
		
		//String textEmail = securityManager.GetEmailFromToken(textToken);
		
		model.put("title", "title");
		model.put("msg", "message");
		model.put("emailtoken", textToken);
		
		return "RegitrationConfirm";
	}
	
	// call function to create user
	@RequestMapping(value = { "/CreateUser" }, method = RequestMethod.GET)
	@ResponseBody 
	public String CreateUser(HttpSession session,@RequestParam("Email") String textEmail, @RequestParam("Password") String textPassword) {
		String msg = "";
		
		try {
			// TODO ; Validate Password
			String result = securityManager.ValidatePassword(textPassword);
			
			if(result.isEmpty()){
				result = securityManager.CreateUser(textEmail, textPassword);
				
				if(result.isEmpty()){
					result = "success";
				}
			}
			
			msg = result;
		} catch (Exception e) {
			logger.error("Method:CreateUser|Err:" + e.getMessage());
			msg = e.getMessage();
		}

		String res = "{\"msg\":\"" + msg + "\"}";
		
		return res;
	}
	
	// show screen registration success
	@RequestMapping(value = "/RegistrationSuccess", method = RequestMethod.GET)
	public String RegistrationSuccess(Map<String, Object> model) {

		logger.debug("RegistrationSuccess() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "RegistrationSuccess";
	}
		
	// call function to login
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	@ResponseBody 
	public String Login(HttpSession session, @RequestParam("Usr") String textUserName, @RequestParam("Pwd") String textPassword) {
		String msg = "";
		
		try {
			String result = securityManager.Login(textUserName, textPassword);
			
			if(result.isEmpty()){
				result = "success";
				
				setLoginAccount(session, textUserName);
			}
			
			msg = result;
		} catch (Exception e) {
			logger.error("Method:Login|Err:" + e.getMessage());
			msg = e.getMessage();
		}

		String res = "{\"msg\":\"" + msg + "\"}";
		
		return res;
	}
	
	// show screen (main page after login)
	@RequestMapping(value = "/mains", method = RequestMethod.GET)
	public String LoginSuccess(HttpSession session, Map<String, Object> model) {
		User usr = securityManager.GetLoginUserProfile(session);
		Curriculum curr = securityManager.GetLoginCurriculum(usr);
		
		logger.debug("LoginSuccess() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		model.put("loginusr",usr);
		model.put("curricul", curr);
		
		return "main";
	}
			
	
	@RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET)
	public String ForgotPassword(Map<String, Object> model) {

		logger.debug("ForgotPassword() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "ForgotPassword";
	}
	
	// call function to validate email and sent mail
	@RequestMapping(value = { "/GenNewPassword" }, method = RequestMethod.GET)
	@ResponseBody 
	public String GenNewPassword(HttpSession session, @RequestParam("UserName") String textUserName) {
		String msg = "";
		
		try {
			String result = securityManager.ForgotPassword(textUserName); 
			
			if(result.isEmpty()){
				msg = "success";
			}
			else{
				msg = result;
			}
		} catch (Exception e) {
			logger.error("Method:GenNewPassword|Err:" + e.getMessage());
			msg = e.getMessage();
		}

		String res = "{\"msg\":\"" + msg + "\"}";
		
		return res;
	}
	
	// show screen registration complete
	@RequestMapping(value = "/ForgotPasswordComplete", method = RequestMethod.GET)
	public String ForgotPasswordComplete(Map<String, Object> model) {

		logger.debug("ForgotPasswordComplete() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "ForgotPasswordComplete";
	}
	
	@RequestMapping(value = "/ChangePassword" , method = RequestMethod.GET)
	public String ChangePassword(HttpSession session, Map<String, Object> model) {
		logger.debug("ChangePassword() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "ChangePassword";
	}
	
	// call function to validate email and sent mail
	@RequestMapping(value = { "/ChangePasswordProcess" }, method = RequestMethod.GET)
	@ResponseBody 
	public String ChangePasswordProcess(HttpSession session, @RequestParam("OldPassword") String textOldPassword, @RequestParam("NewPassword") String textNewPassword) {
		String msg = "";
		
		try {
			String result = securityManager.ChangePassword(session, textOldPassword, textNewPassword); 
			
			if(result.isEmpty()){
				msg = "success";
			}
			else{
				msg = result;
			}
		} catch (Exception e) {
			logger.error("Method:ChangePasswordProcess|Err:" + e.getMessage());
			msg = e.getMessage();
		}

		String res = "{\"msg\":\"" + msg + "\"}";
		
		return res;
	}
	
	@RequestMapping(value = "/ChangePasswordSuccess" , method = RequestMethod.GET)
	public String ChangePasswordSuccess(HttpSession session, Map<String, Object> model) {
		logger.debug("ChangePasswordSuccess() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "ChangePasswordComplete";
	}
	
	private void setLoginAccount(HttpSession session,String UserName){
		Account loginAcc = securityManager.GetLoginAccountProfile(UserName);
		
		session.setAttribute("loginAccount", loginAcc);
	}
}
