package com.gemini.plutus.controller;

import com.gemini.plutus.exception.ExceptionControllerHandler;
import com.gemini.plutus.exception.InvalidParametersException;
import com.gemini.plutus.model.Currency;
import com.gemini.plutus.model.Loan;
import com.gemini.plutus.model.LoanType;
import com.gemini.plutus.service.UserDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/loan")
public class LoanController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerHandler.class);

    private final UserDatabaseService userDatabaseService;

    public LoanController(UserDatabaseService userDatabaseService){
        this.userDatabaseService = userDatabaseService;
    }

    @GetMapping("/interest/calculate")
    public Loan calculateInterest(
            Authentication authentication, @RequestParam LoanType loanType,
            @RequestParam double amount, @RequestParam Currency currency,
            @RequestParam double interestRate, @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate, @RequestParam double margin
    ) throws InvalidParametersException {
        this.validateRequestParams(amount, interestRate, startDate, endDate, margin);
        logger.info("User {} Calculating Interest for Loan Type: {}", authentication.getName(), loanType.getValue());
        Loan loan = new Loan(amount, interestRate, startDate, endDate, loanType, currency, margin);
        this.userDatabaseService.addLoanToHistory(authentication.getName(), loan);
        return loan;
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllLoanTypes(Authentication authentication) {
        logger.info("User {} Getting all Loan Types", authentication.getName());
        return new ResponseEntity<>(Arrays.stream(LoanType.values()).map(LoanType::getValue).toList(), HttpStatus.OK);
    }

    @GetMapping("/currency")
    public ResponseEntity<List<String>> getAllSupportedCurrency(Authentication authentication) {
        logger.info("User {} Getting all supported Currency Types", authentication.getName());
        return new ResponseEntity<>(Arrays.stream(Currency.values()).map(Currency::getCurrency).toList(), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Loan>> getHistoryLoans(Authentication authentication){
        return new ResponseEntity<>(this.userDatabaseService.getUserHistoryLoans(authentication.getName()), HttpStatus.OK);
    }

    private void validateRequestParams(
            double amount, double interestRate, LocalDate startDate,
            LocalDate endDate, double margin
    ) throws InvalidParametersException {
        Map<String, String> errorMap = new HashMap<>();
        if(amount < 0){
            errorMap.put("Amount", "Invalid Amount: Please enter value greater than 0");
        }
        if(interestRate < 0){
            errorMap.put("Interest Rate", "Invalid Interest Rate: Please enter value greater than 0");
        }
        if(endDate.isBefore(startDate)){
            errorMap.put("End Date", "Invalid Dates: End Date cant be before Start Date");
        }
        if(margin < 0){
            errorMap.put("Margin", "Invalid Margin: Please enter value greater than 0");
        }
        if(errorMap.size()>0){
            throw new InvalidParametersException(errorMap.toString());
        }
    }
}
