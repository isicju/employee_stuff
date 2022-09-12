package com.example.demo.controller;

import com.example.demo.SessionLiveObject;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeDetails;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.user.UserApi;
import com.example.demo.services.user.UserApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    public static final String MORTGAGE_URL = "/mortgage/";

    @Autowired
    SessionLiveObject sessionLiveObject;

    @Autowired
    UserApi userApi;
    @Autowired
    MortgageService mortgageService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Date currentTime;

    @Autowired
    Date creationContextTime;


    @GetMapping("/userstats")
    public List<UserApiImpl.UserStatsData> getUserStats() {
        return userApi.getUserStatsData();
    }

    @GetMapping("/users")
    public List<UserApiImpl.User> getUsers() {
        return userApi.getUsers();
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    @GetMapping("/employeesdetails")
    public EmployeeDetails getEmployeeDetails() {
        return employeeRepository.getEmployeeFullDetails(100);
    }


    @GetMapping(MORTGAGE_URL + "{salary}")
    public String getInterest(@PathVariable("salary") Integer salary) {
        return mortgageService.mortgage(salary) + " " + sessionLiveObject + " creation time: " + creationContextTime + " current time: " + currentTime;
    }

//    String status;

//    @GetMapping("/status")
//    public String status() {
//        return this.status;
//    }

}
