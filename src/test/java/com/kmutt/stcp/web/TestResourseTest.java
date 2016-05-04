package com.kmutt.stcp.web;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.entity.Course;
import com.kmutt.stcp.web.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jirapatj on 3/20/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class, TestResourseTest.class})
@WebAppConfiguration
public class TestResourseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

/*    @Test
    public void testCallRestService() throws Exception {
        mockMvc.perform(get("/test/rest/list")
                        .contentType(TestUtils.APPLICATION_JSON_UTF8)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtils.APPLICATION_JSON_UTF8));
    }

    @Test
    public void userNotFound() throws Exception {
        Course c = new Course();
        c.setCode("code");
        c.setName("name");

        mockMvc.perform(post("/test/rest/create")
                        .contentType(TestUtils.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(c))
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;
    }*/


}
