package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static org.postgresql.util.JdbcBlackHole.close;


/**
 * App "Chat" Author: ttanja (school21)
 *
 */
public class Program
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PWD = "docker";
    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    public static void main( String[] args ) throws IOException, SQLException {
        Connection connection = connect();
        runStatement(connection, DB_SCHEMA);
        runStatement(connection, DB_DATA);
        UserRepositoryJdbcImpl userRepository =
                new UserRepositoryJdbcImpl(connection);
        ChatroomRepositoryJdbcImpl chatroomRepository =
                new ChatroomRepositoryJdbcImpl(connection, userRepository);
        MessageRepository messageRepository =
                new MessageRepositoryJdbcImpl(connection, userRepository, chatroomRepository);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter message id:");
        String exit = null;
        Long id;
        while (true){
            exit = bufferedReader.readLine();
            if (exit.equals("exit"))
                break;
            try {
                id = Long.parseLong(exit);
                System.out.println(messageRepository.findById(id).orElse(null));
            } catch (NumberFormatException e) {
                System.out.println("Enter id or \"exit\"");
            }
        }

        close(connection);
    }

    static void runStatement(Connection connection, String fileName) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/src/ex01/Chat/src/main" + fileName;

        Scanner scanner = new Scanner(new File(filePath)).useDelimiter(";");
        try {
            while (scanner.hasNext()) {
                connection.createStatement().execute(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
        System.out.println(ANSI_GREEN + "Completed " + System.getProperty("user.dir")
                + "/ex01/Chat/src/main" + fileName + ANSI_RESET);
    }
    static   Connection connect() {
        Connection  connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = HikariConnect().getConnection();
            System.out.println(ANSI_GREEN + "Connect database successful" + ANSI_RESET);

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
