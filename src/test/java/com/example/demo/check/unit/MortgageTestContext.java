package com.example.demo.check.unit;

import com.example.demo.services.mortage.MortgageService;
import com.example.demo.services.mortage.MortgageServiceMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@TestConfiguration
public class MortgageTestContext {

    @Bean
    MortgageService mortgageService(){
        System.out.println("test context works!");
        return new MortgageServiceMock(111);
    }

    /*@Bean
    MortgageService mortgageRealService(){
        return new MortgageRealService();
    }*/
    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;MODE=MYSQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        // schema init
        Resource initSchema = new ClassPathResource("scripts/schema-h2.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

}
