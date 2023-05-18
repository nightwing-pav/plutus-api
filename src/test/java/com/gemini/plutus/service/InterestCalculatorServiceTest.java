package com.gemini.plutus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(InterestCalculatorService.class)
public class InterestCalculatorServiceTest {

    @Autowired
    private InterestCalculatorService interestCalculatorService;

    @Test
    public void simpleInterestCalculator(){

    }
}
