package com.example.demo.initializations.unit;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.mortage.MortgageService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

//no need @ExtendWith(SpringExtension.class) already in springboottest
@SpringBootTest(classes = TestContext.class)
public class InitializationWithSpringBoot {
    @Autowired
    private MortgageService mortgageService;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    EmployeeRepository employeeRepository;
    @Test
    public void positiveTest(){
        MatcherAssert.assertThat(mortgageService.mortgage(123), is(notNullValue()));
    }

}

