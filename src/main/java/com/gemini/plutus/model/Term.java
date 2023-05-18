package com.gemini.plutus.model;

public enum Term {

    YEARS("Years", 1),
    DAYS("Days", 365);

    private String value;
    private int divider;

    Term(String value, int divider){
        this.value = value;
        this.divider = divider;
    }

    public String getValue(){
        return this.value;
    }

    public int getDivider(){
        return this.divider;
    }


}
