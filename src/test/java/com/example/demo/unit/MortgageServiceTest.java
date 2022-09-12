package com.example.demo.unit;

import com.example.demo.services.mortage.MortgageService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

//@ContextConfiguration(classes = TestContext.class)
//@SpringBootTest(classes = TestContext.class) // override application context
@SpringBootTest
public class MortgageServiceTest {

    @Autowired
    private MortgageService mortgageService;

    @Test()
    public void positiveTest(){
        MatcherAssert.assertThat(mortgageService.mortgage(123), is(notNullValue()));
    }

}
