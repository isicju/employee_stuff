package com.example.demo.initializations.integration;

import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.mortage.MortgageServiceMock;
import com.example.demo.services.user.UserApi;
import com.example.demo.services.user.UserApiImpl;
import com.example.demo.services.user.UserApiMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static java.util.Collections.singletonList;

@TestConfiguration
public class CustomTestWebContext {

    @Bean
    UserApi userApi() {
        UserApiImpl.User user = UserApiImpl.User.builder()
                .id(123)
                .country("Spain")
                .email("some@gmail.com")
                .name("John")
                .build();

        UserApiImpl.UserStatsData userStatsData = UserApiImpl.UserStatsData.builder()
                .country("Spain")
                .occurance(123L)
                .occuranceRate(0.12F)
                .build();
        return new UserApiMock(singletonList(userStatsData), singletonList(user));
    }

    @Bean
    MortgageService mortgageService(){
        return  new MortgageServiceMock(7777);
    }

}
