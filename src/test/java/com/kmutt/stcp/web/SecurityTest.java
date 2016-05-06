package com.kmutt.stcp.web;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.User;
import com.kmutt.stcp.manager.SecurityManager;
import com.kmutt.stcp.repository.AccountRepository;
import com.kmutt.stcp.repository.CurriculumRepository;
import com.kmutt.stcp.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Gift on 04-May-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class})
@WebAppConfiguration
public class SecurityTest {

    @Autowired
    SecurityController securityController;
    @Autowired
    SecurityManager securityManager;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testValidateEmail() {
        String mailNull = securityManager.ValidateEmail("");
        String mailwithCharacter = securityManager.ValidateEmail("student_123@mail.kmutt.ac.th");
        String mailIncomplete = securityManager.ValidateEmail("student_123@mail.ac.th");
        String mailKMUTT = securityManager.ValidateEmail("student@mail.kmutt.ac.th");


        assertEquals("Email Invalid format",mailNull);
        assertEquals(null,mailwithCharacter);
        assertEquals("Email Invalid format",mailIncomplete);
        assertEquals(null, mailKMUTT);
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
    @Transactional
    public void testCreateUser() {
        String result = securityManager.CreateUser("student@mail.kmutt.ac.th","12345678");
        assertEquals(null,result);
    }

    @Test
    @Transactional
    public void createUserControllerResultFormat() {
        String resultPattern = "\\{\"msg\":\".*\"\\}";
        String msg;

        //status message
        msg = securityController.CreateUser(null,"jarupath.j@mail.kmutt.ac.th","11111111");
        assertTrue(msg.matches(resultPattern));

        //null message
        msg = securityController.CreateUser(null,"anonymous@gmail.com","11111111");
        assertTrue(msg.matches(resultPattern));

        //error message
        msg = securityController.CreateUser(null,"jarupath.j@mail.kmutt.ac.th","");
        assertTrue(msg.matches(resultPattern));
    }

    @Test
    @Transactional
    public void createUserControllerSuccessTest() {
        String email = "jarupath.j@mail.kmutt.ac.th";
        String password = "11111111";

        String msg = securityController.CreateUser(null, email, password);
        System.out.println(msg);

        List<Account> users = accountRepository.queryHQL("FROM Account WHERE username='"+email+"'");
        if(users.size()>1) {
            fail("user created duplicate");
        }

        String regex = "\\{\"msg\":\"success\"\\}";
        assertTrue(msg.matches(regex));
    }


}
