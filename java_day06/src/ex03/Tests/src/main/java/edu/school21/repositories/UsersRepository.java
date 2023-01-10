package edu.school21.repositories;

import edu.school21.models.User;

import java.util.Optional;

public interface UsersRepository {
    Optional<User> findByName(String name);

}
