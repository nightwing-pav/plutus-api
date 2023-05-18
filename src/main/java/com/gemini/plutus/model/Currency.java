package com.gemini.plutus.model;

import java.math.BigDecimal;

public enum Currency {

    GBP("British Pounds", "£"),
    USD("Dollars", "$"),
    AUD("Australian Dollars", "$");

    private final String currency;
    private final String symbol;

    Currency(String currency, String symbol){
        this.currency = currency;
        this.symbol = symbol;
    }

    public String getCurrency(){
        return this.currency;
    }

    public String getSymbol(){
        return this.symbol;
    }
}
