package com.kmutt.stcp.web;

import com.kmutt.stcp.manager.SecurityManager;
import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.Subject;
/*import com.kmutt.stcp.dto.MessageResult;
import com.kmutt.stcp.dto.PlanMessageRequest;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Controller
public class SecurityController {
	private final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private SecurityManager securityManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpSession session, Map<String, Object> model) {

		securityManager = this.getCurrentSecurityManager(session);
		
		logger.debug("index() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "index";
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String Register(Map<String, Object> model) {

		logger.debug("Register() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "RegisterUser";
	}
	
	@RequestMapping(value = { "/RegisterUser" }, method = RequestMethod.GET)
	@ResponseBody 
	public String RegisterUser(HttpSession session, @RequestParam("Email") String textEmail) {
		String msg = "";
		
		try {
			
			String validateResult = securityManager.ValidateBeforeSentEmail(textEmail);
			
			if(validateResult.isEmpty()){
				securityManager.sendMail(textEmail);
				msg = "success";
			}
			else{
				msg = validateResult;
			}
		} catch (Exception e) {
			logger.error("Method:SentMailConfirm|Err:" + e.getMessage());
			msg = e.getMessage();
		}

		String res = "{\"msg\":\"" + msg + "\"}";
		
		return res;
	}
	
	/*@RequestMapping(value = { "/RegisterUser" }, method = RequestMethod.GET)
	@ResponseBody 
	public List<Account> RegisterUser(HttpSession session, @RequestParam("Email") String textEmail) {
		List<Account> accountList = new ArrayList();
		
		try {
			accountList = securityManager.TestSQL(textEmail);
			
			securityManager.sendMail(textEmail);
			
			msg = "success";
			
			Account nn = new Account();
			nn.setUsername("aa");
			
			accountList.add(nn);
		} catch (Exception e) {
			logger.error("Method:SentMailConfirm|Err:" + e.getMessage());
			accountList = new ArrayList();
		}

		return accountList;
	}*/
	
	@RequestMapping(value = "/RegistrationComplete", method = RequestMethod.GET)
	public String RegistrationComplete(Map<String, Object> model) {

		logger.debug("RegistrationComplete() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "RegistrationComplete";
	}
	
	@RequestMapping(value = "/ForgotPassword", method = RequestMethod.GET)
	public String ForgotPassword(Map<String, Object> model) {

		logger.debug("ForgotPassword() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "ForgotPassword";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody 
	public List<Subject> Login(HttpSession session, @RequestParam("textsearch") String textSearch) {

		List<Subject> subjectSearched = new ArrayList<>();

		try {
				for (Integer i = 0; i < 5; i++) {
					Subject ss = new Subject();
					
					ss.setDetailThai(textSearch + i.toString());
					
					subjectSearched.add(ss);	
				}
				
			
			
		} catch (Exception e) {

			logger.error("Method:searchSubject|Err:" + e.getMessage());
			subjectSearched = new ArrayList<>();
		}

		return subjectSearched;
	}
	
	
	
	// Method//
		@SuppressWarnings("finally")
		private SecurityManager getCurrentSecurityManager(HttpSession session) {

			SecurityManager _securityManage = null;

			try {

				_securityManage = (SecurityManager) session.getAttribute("securityMng");

				if (_securityManage == null) {
					//TODO: change session name to get account
					_securityManage = new SecurityManager(session);
				}

			} catch (Exception e) {

				logger.error(e.getMessage());

				_securityManage = new SecurityManager(null);

			} finally {

				session.setAttribute("securityMng", _securityManage);
				return _securityManage;

			}

		}
	
}
