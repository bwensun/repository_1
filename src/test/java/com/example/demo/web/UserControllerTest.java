package com.example.demo.web;

import org.hibernate.engine.transaction.spi.IsolationDelegate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Equals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;



/**
 * description
 *
 * @author bowensun
 * @sice 2018/6/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(rollbackFor = Throwable.class)
//@Rollback(false)
public class UserControllerTest {

    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MockHttpSession mockHttpSession;


    @Before
    public void setMockMvc() throws Exception {
        logger.info("======进入before方法=======");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockHttpSession = new MockHttpSession();
//        User user = new User("孙博文", "博文孙", "123456", "123");
//        mockHttpSession.setAttribute("user", user);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void register() throws Exception {
        logger.info("======进入test方法=======");
        mockMvc.perform(MockMvcRequestBuilders.get("/user/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("userName", "孙博文")
                .param("password", "123")
                .session(mockHttpSession)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //throw new ServerCloneException("123");
    }

    @Test
    public void assertThatTest(){
        String value = "123";

    }

    private void assertThat(String value, boolean equals) {
    }

}