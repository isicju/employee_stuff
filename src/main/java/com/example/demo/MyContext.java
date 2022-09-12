package com.example.demo;

import com.example.demo.services.mortage.MortgageRealService;
import com.example.demo.services.mortage.MortgageService;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.util.Date;


@Configuration
public class MyContext {
/*
    @Scope("session")
    @Bean
    public String status(){
        return UUID.randomUUID().toString();
    }*/

    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    SessionLiveObject sessionLiveObject() {
        return new SessionLiveObject(System.currentTimeMillis());
    }

    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    Date currentTime(){
        return new Date();
    }

    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    Date creationContextTime(){
        return new Date();
    }


    //    @Profile("dev")
    @Bean
    DataSource dataSource() throws Exception {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setUrl("jdbc:mariadb://194.169.160.167:3306/hr?user=root&password=my-secret-pw");
        return dataSource;
    }

//    @Profile("prod")
//    @Bean
//    DataSource mariaDbDataSourEmployeeJpaRepoceProd() throws Exception {
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        dataSource.setUrl("jdbc:mariadb://194.169.160.167:3306/hr?user=root&password=my-secret-pw");
//        return dataSource;
//    }

    @Bean
    JdbcTemplate springJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Bean
    MortgageService mortgageService() {
        return new MortgageRealService();
    }

}
