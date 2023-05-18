package com.gemini.plutus.model;

import com.gemini.plutus.service.InterestCalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum LoanType {

    PERSONAL_LOAN("Personal Loan"),
    BUSINESS_LOAN("Business Loan"),
    MORTGAGE("Mortgage") ;

    private final String value;
    private final InterestCalculatorService interestCalculatorService;

    LoanType(String loanType) {
        this.value = loanType;
        this.interestCalculatorService = new InterestCalculatorService();
    }

    public String getValue() {
        return value;
    }

    InterestCalculated calculateInterest(double principle, double interestRate, double margin, long noDays) {
        return this.interestCalculatorService.simpleInterestCalculatorByDays(
                new BigDecimal(principle),
                new BigDecimal(interestRate).divide(BigDecimal.valueOf(100), 13, RoundingMode.HALF_UP),
                noDays
        );
    }


}
