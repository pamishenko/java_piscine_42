package edu.school21.sockets.services;

import edu.school21.sockets.models.User;

public interface UsersService {
    boolean signUp (String username, String password);

    User createUser(String name, String password);
    }
