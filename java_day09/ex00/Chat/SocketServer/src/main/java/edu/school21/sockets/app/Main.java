package edu.school21.sockets.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Parameters(separators = "=")

public class Main {
    public Main() {
    }

    public int getPort() {
        return port;
    }

    @Parameter(names = {"--port", "-p"})
    private int port;

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService usersService = context.getBean("usersService", UsersServiceImpl.class);

        usersService.createUser("Noname", "123");
        usersService.createUser("user", "111");
        usersService.createUser("user1", "3234");
        usersService.createUser("user2", "23423");
        usersService.createUser("user3", "psdfsdf");
        usersService.createUser("user4", "pasdfsdfs");
        usersService.createUser("user5", "passss");
        usersService.createUser("user6", "password");
    }
}

