package com.example.demo.initializations.unit;

import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.mortage.MortgageServiceMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

// excluded from scan during springboottest scan! so specify it directly from springboottest(classes=testcontext.class)
@TestConfiguration
public class TestContext {

    @Bean
    public MortgageService mortgageService() {
        return new MortgageServiceMock(2);
//        return new MortgageRealService();
    }

}