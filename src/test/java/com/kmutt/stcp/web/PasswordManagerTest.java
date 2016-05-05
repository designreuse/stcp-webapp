package com.kmutt.stcp.web;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import com.kmutt.stcp.manager.PasswordManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordManagerTest {

	@Autowired
	PasswordManager passwordManager;
	
	@Test
	public void testEncrypt() throws GeneralSecurityException, UnsupportedEncodingException {
		String test = passwordManager.encrypt("123456789");		
		assertNotNull(test);
	}

}
