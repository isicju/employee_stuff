package com.example.demo.unit;

import com.example.demo.services.mortage.MortgageService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ContextConfiguration
//@Import(TestContext.class)
@ContextConfiguration(classes = TestContext.class)
public class MortgageServiceTestSpringBootTest {

    @Autowired
    private MortgageService mortgageService;

    @Test()
    public void positiveTest(){
        MatcherAssert.assertThat(mortgageService.mortgage(123), is(notNullValue()));
    }


}
