package com.example.demo.repository;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MyDataSource extends MariaDbDataSource implements DataSource {
    public MyDataSource() throws SQLException {
        super();
        this.setUrl("jdbc:mariadb://194.169.160.167:3306/hr?user=root&password=my-secret-pw");
    }
}
