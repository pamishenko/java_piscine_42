package school21.spring.service.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.configs.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;
import school21.spring.service.services.UsersServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    private static final String CREATE_QUERY = "CREATE SCHEMA IF NOT EXISTS users;\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS users.users (\n" +
            "id SERIAL PRIMARY KEY,\n" +
            "email text NOT NULL,\n" +
            "password text\n" +
            ");";

    private static final String INIT_DATA = "INSERT INTO users.users (id, email)\n" +
            "VALUES (1, 'pavel1@mail.ru');\n" +
            "INSERT INTO users.users (id, email)\n" +
            "VALUES (2, 'pavel2@mail.ru');\n" +
            "INSERT INTO users.users (id, email)\n" +
            "VALUES (3, 'pavel3@mail.ru');\n" +
            "INSERT INTO users.users (id, email)\n" +
            "VALUES (4, 'pavel4@mail.ru');\n" +
            "INSERT INTO users.users (id, email)\n" +
            "VALUES (5, 'pavel5@mail.ru');";


    public static void main( String[] args ) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class);
        Connection connection = context.getBean("dataSource", DataSource.class).getConnection();
        PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);
        statement.execute();


        System.out.println("INIT DATA: ");
        statement = connection.prepareStatement(INIT_DATA);
        statement.execute();
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("ADD new: ");
        usersRepository.save(new User("newemail@maild.ru"));
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("delete item id=2:");
        usersRepository.delete(2L);
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("find by id --------> " + usersRepository.findById(4L).get());
        System.out.println("find by email -----> " + usersRepository.findByEmail("newemail@maild.ru").get());
        usersRepository = context.getBean("userRepositoryJdbc", UsersRepositoryJdbcImpl.class);


        System.out.println("Change datasource to hikari:");
        usersRepository = context.getBean("userRepositoryJdbc", UsersRepositoryJdbcImpl.class);
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("ADD new: ");
        usersRepository.save(new User("newemail2@maild.ru"));
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("delete item id=2:");
        usersRepository.delete(3L);
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("find by id --------> " + usersRepository.findById(4L).get());
        System.out.println("find by email -----> " + usersRepository.findByEmail("newemail@maild.ru").get());

        UsersService usersService = new UsersServiceImpl();

        usersRepository.findAll().forEach(System.out::println);
    }

}
