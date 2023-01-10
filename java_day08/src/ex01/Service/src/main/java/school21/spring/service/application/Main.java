package school21.spring.service.application;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import javax.xml.transform.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main( String[] args ) throws FileNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        UsersRepository usersRepository = context.getBean("userRepositoryJdbc", UsersRepository.class);
        usersRepository.save(new User("userRepositoryJdbc@mail.ru"));
        usersRepository.findAll().forEach(System.out::println);
        usersRepository.delete(1L);
        usersRepository.findAll().forEach(System.out::println);
        System.out.println("----- change datasource to userRepositoryJdbcTemplate -----");
        usersRepository = context.getBean("userRepositoryJdbcTemplate", UsersRepository.class);
        usersRepository.findAll().forEach(System.out::println);
        usersRepository.delete(2L);
        usersRepository.findAll().forEach(System.out::println);
        usersRepository.save(new User("userRepositoryJdbcTemplate@mail.ru"));
        System.out.println("find by id --------> " + usersRepository.findById(3L).get());
        System.out.println("find by email -----> " + usersRepository.findByEmail("userRepositoryJdbc@mail.ru").get());

    }
}
