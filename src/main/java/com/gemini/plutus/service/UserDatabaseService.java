package com.gemini.plutus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gemini.plutus.model.Loan;
import org.springframework.stereotype.Service;

import com.gemini.plutus.exception.UserNotFoundException;
import com.gemini.plutus.model.User;

@Service
public class UserDatabaseService {
    private Map<String, User> userDatabase;
    private Map<String, List<Loan>> history;

    public UserDatabaseService() {
        this.setupUserDatabase();
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        if (!this.userDatabase.containsKey(username)) {
            throw new UserNotFoundException("User: " + username + " Not Found In Database");
        }
        return this.userDatabase.get(username);
    }

    private void setupUserDatabase() {
        this.userDatabase = new HashMap<>();
        this.history = new HashMap<>();
        this.userDatabase.put("admin", new User(1, "admin", "Pavinder", "Singh"));
        this.userDatabase.put("luke", new User(2, "luke", "Luke", "Skywalker"));
        this.userDatabase.put("obi-wan", new User(3, "obi-wan", "Obi-Wan", "Kenobi"));
    }

    public List<Loan> getUserHistoryLoans(String username) {
        if (this.history.containsKey(username)) {
            return this.history.get(username);
        }
        return new ArrayList<>();
    }

    public void addLoanToHistory(String username, Loan loan) {
        if (!this.doesLoanExist(username, loan)) {
            if (this.history.containsKey(username)) {
                this.history.get(username).add(loan);
            } else {
                List<Loan> historyLoans = new ArrayList<>();
                historyLoans.add(loan);
                this.history.put(username, historyLoans);
            }
        }
    }

    private boolean doesLoanExist(String username, Loan loan) {
        if (this.history.containsKey(username)) {
            List<Loan> historyLoans = this.history.get(username);
            return historyLoans.stream().anyMatch(p -> p.equals(loan));
        }
        return false;
    }


}
