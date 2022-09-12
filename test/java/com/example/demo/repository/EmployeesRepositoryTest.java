package com.example.demo.repository;

import com.example.demo.services.mortage.MortgageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

//@ActiveProfiles("test")
@SpringBootTest(classes = RepositoryTestContext.class)
//@Import(RepositoryTestContext.class)
//@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true", classes = RepositoryTestContext.class)
//@ContextConfiguration(classes = {RepositoryTestContext.class})
//@Import(RepositoryTestContext.class)
public class EmployeesRepositoryTest {
    //    @TestConfiguration
//    public static class RepositoryTestContext {
//
//        @Bean
//        public MortgageService embeddedDataSource() {
//            return new MortgageServiceMock(1);
//        }
//
//
//    }
    @Autowired
    private Environment environment;

    @Autowired
    private MortgageService mortgageService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void test() {
        System.out.println(employeeRepository.getEmployees());
    }
}
