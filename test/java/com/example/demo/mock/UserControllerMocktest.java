package com.example.demo.mock;

import com.example.demo.controller.UserController;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.user.UserApi;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerMocktest {

    @Autowired
    UserController userController;

    @MockBean
    UserApi userApi;

    @MockBean
    MortgageService mortgageService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void kek(){
        when(mortgageService.mortgage(anyInt())).thenReturn(999);
        MatcherAssert.assertThat(userController.getInterest(2), equalTo(999));
    }

    @Test
    public void emptyEmployees(){
        when(employeeRepository.getEmployees()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(0,userController.getEmployees().size());
    }



}
