
package edu.school21.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmbeddedDataSourceTest {

    private static final String DB_URL = "jdbc:hsqldb:mem:test";
    private static final String DB_USER = "";
    private static final String DB_PWD = "";
    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    @Test
    @BeforeEach
    public void init() throws Exception {
        EmbeddedDatabaseBuilder dataSource = new EmbeddedDatabaseBuilder();
        DataSource ds = dataSource
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        ds.getConnection();
        Assertions.assertNotNull(ds);
        Connection connection = connect();
        runStatement(connection, DB_SCHEMA);
        runStatement(connection, DB_DATA);

        connection.close();
    }

    @Test
    public void getConnectionTest() throws Exception{
        assertTrue(HikariConnect().getConnection() != null);
    }
    static void runStatement(Connection connection, String fileName) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/src/test" + fileName;

        Scanner scanner = new Scanner(new File(filePath)).useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
    static Connection connect() {
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
    private static HikariDataSource HikariConnect() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PWD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return (new HikariDataSource(config));
    }
}