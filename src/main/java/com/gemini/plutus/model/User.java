package com.gemini.plutus.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int userId;
    private String username;
    private String firstName;
    private String lastName;

    public User(){}

    public User(int userId, String username, String firstName, String lastName){
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setFirstname(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
