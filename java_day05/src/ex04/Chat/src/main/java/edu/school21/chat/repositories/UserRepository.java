package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User>  findById(Long id) throws SQLException;

    abstract void save(User user);
    List<User> findAll();

    List<User> findAll(int page, int size);
}
