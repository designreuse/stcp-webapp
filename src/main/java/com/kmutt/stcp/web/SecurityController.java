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
	public String RegisterUser(Map<String, Object> model) {

		logger.debug("RegisterUser() is executed!");

		model.put("title", "title");
		model.put("msg", "message");
		
		return "RegisterUser";
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
	
	@RequestMapping(value = { "/SentMailConfirm" }, method = RequestMethod.GET)
	@ResponseBody 
	public List<Account> SentMailConfirm(HttpSession session, @RequestParam("email") String textUserName) {
		List<Account> accountList =  new ArrayList<>();
		
		try {
			securityManager.sendMail(textUserName);
			
			Account acc = new Account();
			acc.setUsername(textUserName);
			
			accountList.add(acc);
		} catch (Exception e) {
			logger.error("Method:SentMailConfirm|Err:" + e.getMessage());
			Account acc = new Account();
			acc.setUsername(e.getMessage());
			
			accountList.add(acc);
		}


		return accountList;

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
