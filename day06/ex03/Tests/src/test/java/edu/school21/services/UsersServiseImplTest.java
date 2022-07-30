package edu.school21.services;


import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import edu.school21.repositories.UsersRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiseImplTest {

    @Mock
    Connection connection;
    @Mock
    UsersServiceImpl usersService;

    @Mock
    UsersRepository usersRepository;

    @Mock
    User user;

    @BeforeEach
    void init(){
        usersService = new UsersServiceImpl(connection);
        usersRepository = new UsersRepositoryImpl(connection);
        user = new User("test", "test");
    }

    @Test
    void userServiceTestNotUser(){
        assertFalse( usersService.authenticate("someUser", "pass"));
    }

    @Test
    void userServiceTestNotCorrectPassword(){
        assertFalse( usersService.authenticate(usersRepository.findByName("test").get().getLogin(), "s"));
    }

    void userServiceTestCorrectPassword(){
        assertFalse( usersService.authenticate("someUser", "pass"));
    }
}
