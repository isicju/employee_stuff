package com.example.demo.exampleMock;

import com.example.demo.controller.UserController;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.user.UserApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class ControllerWithMock {

    @Autowired
    UserController userController;

    @MockBean
    EmployeeRepository employeeRepository;

    @MockBean
    UserApi userApi;

    @MockBean
    MortgageService mortgageService;

    @Test
    public void testOk(){
        when(employeeRepository.getEmployees()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(0,userController.getEmployees().size());
    }



}
