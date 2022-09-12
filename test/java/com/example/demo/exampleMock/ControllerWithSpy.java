package com.example.demo.exampleMock;

import com.example.demo.controller.UserController;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = RepositoryTestContext.class)
public class ControllerWithSpy {

    @Autowired
    UserController userController;

    @SpyBean
    EmployeeRepository employeeRepository;

    @MockBean
    UserApi userApi;

    @MockBean
    MortgageService mortgageService;

    @Captor
    ArgumentCaptor<Integer> employeeCature;

    @Test
    public void testOk(){
        userController.getEmployees();


        verify(employeeRepository, times(1)).getEmployees();
//        verify(employeeRepository, ).getEmployees();



        Integer employees = employeeCature.capture();
        System.out.println(employees);

//        assertTrue(verify(employeeRepository.getEmployees()).size() > 0);

//        Assertions.assertEquals(0,userController.getEmployees().size());


    }

    @Test
    public void testOk2(){
        userController.getEmployeeDetails();

        verify(employeeRepository, times(1)).getEmployeeFullDetails(employeeCature.capture());
//        verify(employeeRepository, ).getEmployees();


        Integer employees = employeeCature.getValue();
        System.out.println(employees);

//        assertTrue(verify(employeeRepository.getEmployees()).size() > 0);

//        Assertions.assertEquals(0,userController.getEmployees().size());


    }

}
