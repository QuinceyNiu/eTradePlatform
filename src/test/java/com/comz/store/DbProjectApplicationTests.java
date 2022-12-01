package com.comz.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class DbProjectApplicationTests {

    @Autowired // 自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() {
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
