package edu.school21.chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static org.postgresql.util.JdbcBlackHole.close;

/**
 * App "Chat" Author: ttanja (school21)
 *
 */
public class App 
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private static final String DB_USER = "pavel";
    private static final String DB_PWD = "pa55";
    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    public static void main( String[] args ) throws FileNotFoundException {
        Connection connection = connect();
        runStatement(connection, DB_SCHEMA);
        runStatement(connection, DB_DATA);
        close(connection);
    }

    static void runStatement(Connection connection, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(
                new File(System.getProperty("user.dir") + "/day05/ex00/Chat/src/main" + fileName))
                .useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
        System.out.println(ANSI_GREEN + "Completed " + System.getProperty("user.dir")
                + "/ex00/Chat/src/main" + fileName + ANSI_RESET);
    }
    static   Connection connect() {
        Connection  connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println(ANSI_GREEN + "Connect database successful" + ANSI_RESET);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
