package com.sysoiev.app.repository.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            try (InputStream inputStream = JdbcSpecialtyRepository.class.getResourceAsStream("/db.properties")) {
                properties.load(inputStream);
            }
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection= DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
