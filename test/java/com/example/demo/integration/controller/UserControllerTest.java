package com.example.demo.integration.controller;


import com.example.demo.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    UserController userController;

    @Test
    public void simple(){

    }

}
