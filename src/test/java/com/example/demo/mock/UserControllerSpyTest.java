package com.example.demo.mock;

import com.example.demo.controller.UserController;
import com.example.demo.exampleMock.RepositoryTestContext;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.user.UserApi;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RepositoryTestContext.class)
public class UserControllerSpyTest {

    @Autowired
    UserController userController;

    @SpyBean
    EmployeeRepository employeeRepository;

    @MockBean
    UserApi userApi;

    @MockBean
    MortgageService mortgageService;

    @Test
    public void testOk(){
        userController.getEmployees();
        verify(employeeRepository, times(1)).getEmployees();
    }

    @Captor
    ArgumentCaptor<Integer> employeeCapture;

    @Test
    public void testOkDetails(){
        userController.getEmployeeDetails();
        verify(employeeRepository, times(1)).getEmployeeFullDetails(employeeCapture.capture());
        assertEquals(100, employeeCapture.getValue());
    }

}
