package com.example.demo.ui;

import com.example.demo.exampleMock.RepositoryTestContext;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = RepositoryTestContext.class)
public class UITest {


    @Test
    public void ok(){
        //<a>Analytics</a>
//        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/mynetwork/invitation-manager/sent/");
        driver.findElement(By.linkText("Analytics")).click();

        System.out.println(driver.findElement(By.tagName("h")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void sikuli(){

    }

}
