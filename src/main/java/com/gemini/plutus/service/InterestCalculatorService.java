package com.gemini.plutus.service;

import com.gemini.plutus.model.InterestCalculated;
import com.gemini.plutus.model.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InterestCalculatorService {

    private final Logger logger = LoggerFactory.getLogger(InterestCalculatorService.class);

    public InterestCalculatorService() {
    }

    public InterestCalculated simpleInterestCalculatorByDays(BigDecimal principle, BigDecimal interestRate, long days) {
        //Interest = (principle) * (interest rate) * (1 + (days/365))
        //TODO: Interest plus margin = principle * (interest rate + margin rate) * ( 1 + (days/365) )

        BigDecimal noDays = new BigDecimal(days);
        BigDecimal time = noDays.divide(BigDecimal.valueOf(365), 13, RoundingMode.HALF_UP).multiply(interestRate);
        time = time.add(BigDecimal.valueOf(1));

        BigDecimal total = principle.multiply(time).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interest = total.subtract(principle);

        BigDecimal principleInterest = principle.multiply(interestRate);
        BigDecimal dailyInterest = principleInterest.divide(BigDecimal.valueOf(365), 13, RoundingMode.HALF_UP);

        InterestCalculated interestCalculated = new InterestCalculated(principle, interest, noDays.intValue());
        interestCalculated.setDailyInterestAccuralAmount(dailyInterest.setScale(2, RoundingMode.HALF_UP).doubleValue());
        interestCalculated.setDailyInterestWithMargin(0);
        interestCalculated.setDailyInterestWithoutMargin(0);
        logger.info("Interest: {}", interest);
        logger.info("Daily Interest: {}", dailyInterest);

        return interestCalculated;

    }
}
