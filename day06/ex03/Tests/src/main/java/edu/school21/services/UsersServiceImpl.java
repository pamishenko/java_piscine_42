package edu.school21.services;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepositoryImpl;

import java.sql.Connection;

public class UsersServiceImpl {
    Connection connection;

    public UsersServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public boolean authenticate(String login, String password) {
        User user = new UsersRepositoryImpl(connection).findByName(login).get();
        if (user == null)
                return false;
        if (user.getPassword().equals(password)){
            user.setAuthenticationSuccessStatus(true);
            return true;
        }
        return false;
    }
}
