package com.gemini.plutus.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private double amount;
    private double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LoanType loanType;
    private Currency currency;
    private double margin;
    private InterestCalculated interestCalculated;

    public Loan(double amount, double interestRate, LocalDate startDate, LocalDate endDate, LoanType loanType, Currency currency, double margin){
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanType = loanType;
        this.currency = currency;
        this.margin = margin;
        this.interestCalculated = this.loanType.calculateInterest(this.amount, this.interestRate, this.margin, this.calculateDays());
    }

    public double getAmount(){
        return this.amount;
    }

    public double getInterestRate(){
        return this.interestRate;
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public LoanType getLoanType(){
        return this.loanType;
    }

    public Currency getCurrency(){
        return this.currency;
    }

    public double getMargin(){
        return this.margin;
    }

    public InterestCalculated getInterestCalculated(){
        return this.interestCalculated;
    }

    public void setInterestCalculated(InterestCalculated interestCalculated){
        this.interestCalculated = interestCalculated;
    }

    public long calculateDays(){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setLoanType(LoanType loanType){
        this.loanType = loanType;
    }

    public void setLoanType(String loanType){
        this.loanType = LoanType.valueOf(loanType);
    }

    public void setCurrency(Currency currency){
        this.currency = currency;
    }

    public void setCurrency(String currency){
        this.currency = Currency.valueOf(currency);
    }

    public void setMargin(double margin){
        this.margin = margin;
    }

    public boolean equals(Loan loan){
        return this.getAmount() == loan.getAmount()
                && this.getInterestRate() == loan.getInterestRate()
                && this.getLoanType().equals(loan.getLoanType())
                && this.getCurrency().equals(loan.getCurrency())
                && this.getStartDate().equals(loan.getStartDate())
                && this.getEndDate().equals(loan.getEndDate())
                && this.getMargin() == loan.getMargin();
    }
}
