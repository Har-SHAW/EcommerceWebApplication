package com.project.ecommerce.database;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class JDBCTest {
    @Autowired
    DataSource dataSource;

    @Test
    void databaseTest() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println(connection.getCatalog());
            String database = connection.getCatalog();
            Assertions.assertThat(database).isEqualTo("ecommerce");
        }
    }
}
