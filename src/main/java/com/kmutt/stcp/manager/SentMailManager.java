package com.kmutt.stcp.manager;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import com.kmutt.stcp.manager.PasswordManager;

@Component("sentmailManager")
public class SentMailManager {
	 final String hostname = "smtp.gmail.com";
	 final String portnumber = "587";
	 final String username = "testsendsit@gmail.com";
	 final String password = "testsendsit1234";
	 final String from = "testsendsit@gmail.com";
	
	 private PasswordManager _passwordManager;
	 
	 public SentMailManager(){
		 _passwordManager = new PasswordManager();
	 }
	 
	 public void SentMail(String email){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", hostname);
		props.put("mail.smtp.port", portnumber);
	     
	  // Get the Session object.
	    Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });
	    
	    try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         try {
				message.setFrom(new InternetAddress(from,"Student Course Planner"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(email));

	         // Set Subject: header field
	         message.setSubject("Student Course Planner Email Confirm");

	         // Now set the actual message
	         message.setContent(GenerateEmailConfirmationMessage(email), "text/html; charset=utf-8");

	         // Send message
	         Transport.send(message);

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	     
	 }

	 public void SentMail(String email,String newpassword){
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", hostname);
			props.put("mail.smtp.port", portnumber);
		     
		  // Get the Session object.
		    Session session = Session.getInstance(props,
		      new javax.mail.Authenticator() {
		         protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(username, password);
		         }
		      });
		    
		    try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         try {
					message.setFrom(new InternetAddress(from,"Student Course Planner"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		         InternetAddress.parse(email));

		         // Set Subject: header field
		         message.setSubject("Student Course Planner Email Confirm");

		         // Now set the actual message
		         message.setContent(GenerateEmailNewPasswordMessage(newpassword), "text/html; charset=utf-8");

		         // Send message
		         Transport.send(message);

		      } catch (MessagingException e) {
		            throw new RuntimeException(e);
		      }
		     
		 }

	 
	 private String GenerateEmailConfirmationMessage(String Email){
		 String message = "";
		 
		 message += "<h4>Hi</h4><p>";
		 message += "<h4>Welcome to Student Course Planner! Please verify your email address below.</h4>";
		 message += "<hr>";
		 message += "<center><h5>";
		 message += "<a href=\"" + GenerateLink(Email) + "\">";
		 message += GenerateLink(Email);
		 message += "</a>";
		 message += "</h5></center>";
		 message += "<hr>";
		 message += "<p>";
		 message += "<p>";
		 message += "<h4>Thanks,<br>";
		 message += "The Student Course Planner Team</h4>";
		 
		 return message;
	 }
	 
	 private String GenerateLink(String Email){
		 String link = "";
		 String token = Email;
		 
		 link += "http://localhost:8080/stcp/RegitrationConfirm?token=";
		 link += Email; // GenerateToken(Email);
		 
		 return link;
	 }
	 
	 private String GenerateToken(String Email){
		 String token = "";
		 try {
			 token  = _passwordManager.encrypt(Email);
		 } catch (Exception e) {
		 }
		 
		 return token;
	 }

	 private String GenerateEmailNewPasswordMessage(String Password){
		 String message = "";
		 
		 message += "<h4>Hi</h4><p>";
		 message += "<h4>Your new password to access Student Course Planner is:</h4><p><p>";
		 message += "<h5>";
		 message += Password;
		 message += "</h5>";;
		 message += "<p>";
		 message += "<p>";
		 message += "<h4>Thanks,<br>";
		 message += "The Student Course Planner Team</h4>";
		 
		 return message;
	 }
}
