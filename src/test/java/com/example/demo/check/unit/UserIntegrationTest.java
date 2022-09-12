package com.example.demo.check.unit;

import com.example.demo.controller.UserController;
import com.example.demo.services.mortage.MortgageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MortgageTestContext.class)
public class UserIntegrationTest {

    @Autowired
    MortgageService mortgageService;
    @Autowired
    UserController userController;

    @Test
    public void ok(){
        assertEquals(111,userController.getInterest(1));
    }

}
