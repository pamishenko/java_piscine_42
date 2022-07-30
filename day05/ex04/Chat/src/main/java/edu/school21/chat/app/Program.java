package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.ChatroomRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessageRepository;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
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
    private static final String DB_USER = "pavel";
    private static final String DB_PWD = "pa55";
    private static final String DB_SCHEMA = "/resources/schema.sql";
    private static final String DB_DATA = "/resources/data.sql";

    public static void main( String[] args ) throws IOException, SQLException, NotSavedSubEntityException {
        Connection connection = connect();
        runStatement(connection, DB_SCHEMA);
        runStatement(connection, DB_DATA);
        UserRepositoryJdbcImpl userRepository =
                new UserRepositoryJdbcImpl(connection);
        ChatroomRepositoryJdbcImpl chatroomRepository =
                new ChatroomRepositoryJdbcImpl(connection, userRepository);
        MessageRepository messageRepository =
                new MessageRepositoryJdbcImpl(connection, userRepository, chatroomRepository);

        Optional<Message> messageOptional = messageRepository.findById(2L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setMessageText("Update text!!!");
            message.setDateTime(null);
            messageRepository.update(message);
        }
        close(connection);
    }

    static void runStatement(Connection connection, String fileName) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/day05/ex03/Chat/src/main" + fileName;

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
                + "/ex02/Chat/src/main" + fileName + ANSI_RESET);
    }

    static   Connection connect() throws NotSavedSubEntityException {
        Connection  connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = HikariConnect().getConnection();
            System.out.println(ANSI_GREEN + "Connect database successful" + ANSI_RESET);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new NotSavedSubEntityException(e);
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
