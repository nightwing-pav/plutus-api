package com.gemini.plutus.service;

import com.gemini.plutus.exception.UserNotFoundException;
import com.gemini.plutus.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserDatabaseService.class)
public class UserDatabaseServiceTest {

    @Autowired
    private UserDatabaseService userDatabaseService;

    @Test
    public void getAllUsersByUsername() throws UserNotFoundException {
        User user = this.userDatabaseService.getUserByUsername("admin");
        assertEquals("admin", user.getUsername());
        user = this.userDatabaseService.getUserByUsername("luke");
        assertEquals("luke", user.getUsername());
        user = this.userDatabaseService.getUserByUsername("obi-wan");
        assertEquals("obi-wan", user.getUsername());
    }

    @Test(expected = UserNotFoundException.class)
    public void getNonExistingUser() throws UserNotFoundException {
        this.userDatabaseService.getUserByUsername("No_User");
    }
}
