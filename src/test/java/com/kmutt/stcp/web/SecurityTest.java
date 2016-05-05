package com.kmutt.stcp.web;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.manager.SecurityManager;
import com.kmutt.stcp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Gift on 04-May-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class})
@WebAppConfiguration
public class SecurityTest {

    @Autowired
    SecurityManager securityManager;
    @Autowired
    UserRepository userRepository;


    @Test
    public void testValidateEmail() {
        String mailNull = securityManager.ValidateEmail("");
        String mailKMUTT = securityManager.ValidateEmail("student@mail.kmutt.ac.th");

        assertEquals("Email Invalid format",mailNull);
        assertEquals(null,mailKMUTT);
    }

    @Test
    public void testSendMail() {
        securityManager.SendMail("student@mail.kmutt.ac.th");
    }

    @Test
    public void testValidatePassword() {
        String passwordCorrect = securityManager.ValidatePassword("123456789");
        String passwordFail = securityManager.ValidatePassword("123456");
        String passwordNull = securityManager.ValidatePassword("");

        assertEquals("",passwordCorrect);
        assertEquals("Password must have length more than 8 character",passwordFail);
        assertEquals("Password must have length more than 8 character",passwordNull);
    }

    @Test
    public void testCreateUser() {
        String result = securityManager.CreateUser("student@mail.kmutt.ac.th","12345678");
        assertEquals(null,result);
    }

    @Test
    public void createUserNotAvailableEmailTest() {
        String result = securityManager.CreateUser("eee@mail.kmutt.ac.th","11111111");
        if(result.isEmpty()){
            fail(result);
        }
    }

    @Test
    public void createUserIsDuplicateTest() {

    }


}
