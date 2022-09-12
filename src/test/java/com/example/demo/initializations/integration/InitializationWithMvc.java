package com.example.demo.initializations.integration;


import com.example.demo.controller.UserController;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.user.UserApi;
import com.example.demo.services.user.UserApiImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = CustomTestWebContext.class)
public class InitializationWithMvc {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }


    @Test
    public void checkController() throws Exception {

        Employee employee =  Employee.builder().firstName("joh").lastName("ke").salary(12).email("ke@tkc.om").build();

        when(employeeRepository.getEmployees()).thenReturn(Arrays.asList(employee));

        MvcResult mvcResult = this.mockMvc.perform(get("/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(" [{\"id\":null,\"firstName\":\"joh\",\"lastName\":\"ke\",\"hireDate\":null,\"email\":\"ke@tkc.om\",\"salary\":12}]"))
                .andReturn();

    }

//
//    @Test
//    public void checkController() throws Exception {
//
//            when(employeeRepository.getEmployees()).thenReturn(new ArrayList<>());
//            Assertions.assertEquals(0,userController.getEmployees().size());
//
//        MvcResult mvcResult = this.mockMvc.perform(get(MORTGAGE_URL + "123"))
//                .andDo(print())
//                .andExpect(status().isOk())
////                .andExpect(content().json())
//                .andReturn();
//
//    }

}
