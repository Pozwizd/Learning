package utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {

    private static final BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(ConfigLoader.getUrl());
        dataSource.setUsername(ConfigLoader.getUsername());
        dataSource.setPassword(ConfigLoader.getPassword());
        dataSource.setMinIdle(15);
        dataSource.setMaxIdle(25);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
