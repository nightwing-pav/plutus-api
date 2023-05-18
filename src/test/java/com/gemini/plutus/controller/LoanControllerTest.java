package com.gemini.plutus.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.plutus.model.LoanType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUpTest(){
    }

    @Test
    @WithMockUser(username = "admin", password = "admin")
    public void getUserDetails() throws Exception {
        MvcResult result = mockMvc.perform(get("/loan/types"))
                .andExpect(status().isOk())
                .andReturn();

        List<String> expectedLoanTypes = Arrays.stream(LoanType.values()).map(LoanType::getValue).toList();
        List<String> actualLoanTypes = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});

        assertEquals(expectedLoanTypes, actualLoanTypes);
    }

}
