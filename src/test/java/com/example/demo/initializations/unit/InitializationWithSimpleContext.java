package com.example.demo.initializations.unit;

import com.example.demo.services.mortage.MortgageService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


@ExtendWith(SpringExtension.class)
@Import(CustomTestContext.class)

public class InitializationWithSimpleContext {
    @Autowired
    private MortgageService mortgageService;
    @Autowired
    ApplicationContext applicationContext;
    @Test()
    public void positiveTest() {
        MatcherAssert.assertThat(mortgageService.mortgage(123), is(notNullValue()));
    }
}
