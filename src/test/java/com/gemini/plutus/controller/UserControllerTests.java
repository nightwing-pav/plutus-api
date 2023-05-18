package com.gemini.plutus.controller;

import com.gemini.plutus.model.User;
import com.gemini.plutus.service.UserDatabaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDatabaseService userDatabaseService;

    private final User mockUser = new User(1, "admin", "Darth", "Vader");

    @Before
    public void setUpTest() throws Exception{
        when(this.userDatabaseService.getUserByUsername("admin")).thenReturn(this.mockUser);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void getUserDetails() throws Exception {
        mockMvc.perform(get("/user"))
            .andExpect(status().isOk())
            .andReturn();
    }

}
