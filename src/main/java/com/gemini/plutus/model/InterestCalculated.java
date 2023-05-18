package com.gemini.plutus.model;

import java.math.BigDecimal;

public class InterestCalculated {

    private BigDecimal principleAmount;
    private BigDecimal interest;
    private BigDecimal dailyInterestAccuralAmount;
    private int totalNoDays;
    private BigDecimal dailyInterestWithMargin;
    private BigDecimal dailyInterestWithoutMargin;

    public InterestCalculated(BigDecimal principleAmount, BigDecimal interest){
        this.principleAmount = principleAmount;
        this.interest = interest;
        this.totalNoDays = 0;
        this.dailyInterestAccuralAmount = BigDecimal.valueOf(0);
        this.dailyInterestWithMargin = BigDecimal.valueOf(0);
        this.dailyInterestWithoutMargin = BigDecimal.valueOf(0);
    }

    public InterestCalculated(BigDecimal principleAmount, BigDecimal interest, int totalNoDays){
        this.principleAmount = principleAmount;
        this.interest = interest;
        this.totalNoDays = totalNoDays;
        this.dailyInterestAccuralAmount = BigDecimal.valueOf(0);
        this.dailyInterestWithMargin = BigDecimal.valueOf(0);
        this.dailyInterestWithoutMargin = BigDecimal.valueOf(0);
    }

    public InterestCalculated(double principleAmount, double interest){
        this.principleAmount = BigDecimal.valueOf(principleAmount);
        this.interest = BigDecimal.valueOf(interest);
    }

    public double getPrincipleAmount(){
        return this.principleAmount.doubleValue();
    }

    public double getInterest(){
        return this.interest.doubleValue();
    }

    public double getTotal(){
        return this.principleAmount.add(this.interest).doubleValue();
    }

    public double getDailyInterestAccuralAmount(){
        return this.dailyInterestAccuralAmount.doubleValue();
    }

    public int getTotalNoDays(){
        return this.totalNoDays;
    }

    public double getDailyInterestWithMargin(){
        return this.dailyInterestWithMargin.doubleValue();
    }

    public double getDailyInterestWithoutMargin(){
        return this.dailyInterestWithoutMargin.doubleValue();
    }

    public void setPrincipleAmount(double principleAmount){
        this.principleAmount = BigDecimal.valueOf(principleAmount);
    }

    public void setInterest(double interest){
        this.interest = BigDecimal.valueOf(interest);
    }

    public void setDailyInterestAccuralAmount(double dailyInterestAccuralAmount){
        this.dailyInterestAccuralAmount = BigDecimal.valueOf(dailyInterestAccuralAmount);
    }

    public void setTotalNoDays(int totalNoDays){
        this.totalNoDays = totalNoDays;
    }

    public void setDailyInterestWithMargin(double dailyInterestWithMargin){
        this.dailyInterestWithMargin = new BigDecimal(dailyInterestWithMargin);
    }

    public void setDailyInterestWithoutMargin(double dailyInterestWithoutMargin){
        this.dailyInterestWithoutMargin = new BigDecimal(dailyInterestWithoutMargin);
    }
}
