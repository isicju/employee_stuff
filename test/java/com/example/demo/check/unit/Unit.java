package com.example.demo.check.unit;


import com.example.demo.services.mortage.MortgageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

//@ExtendWith(SpringExtension.class) // junit5 runner
//@Import(MortgageTestContext.class)

@SpringBootTest(classes = MortgageTestContext.class)
public class Unit {

    @Autowired
    MortgageService mortgageService;

    @Autowired
    ApplicationContext applicationContext;

    @BeforeEach
    public void initBeforeEach(){
        System.out.println("initBeforeEach");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("AfterEach");
    }

    @Test
    public void kekTes(){
        System.out.println("kek");
        Assert.notNull(mortgageService);
    }


}
