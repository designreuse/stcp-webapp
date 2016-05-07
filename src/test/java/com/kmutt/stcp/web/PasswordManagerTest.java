package com.kmutt.stcp.web;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.manager.PasswordManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class})
@WebAppConfiguration
public class PasswordManagerTest {

	@Autowired
	PasswordManager passwordManager;
	
	@Test
	public void testEncrypt() throws GeneralSecurityException, UnsupportedEncodingException {
		String test = passwordManager.encrypt("123456789");		
		assertNotNull(test);
	}

}
