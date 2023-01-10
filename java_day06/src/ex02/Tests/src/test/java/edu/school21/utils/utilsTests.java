package edu.school21.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class utilsTests {
    private static final String DB_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String DB_URL = "jdbc:hsqldb:mem:test";
    private static final String DB_USER = "";
    private static final String DB_PWD = "";
    public static final String DB_SCHEMA = "/resources/schema.sql";
    public static final String DB_DATA = "/resources/data.sql";

    public static Connection connect() {
        Connection  connection = null;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDataSource");
            connection = HikariConnect().getConnection();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static HikariDataSource HikariConnect() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DB_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PWD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return (new HikariDataSource(config));
    }
}
