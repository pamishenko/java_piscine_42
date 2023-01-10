package edu.school21.chat.repositories;


import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository{
    private final Connection    connection;
    private static final String QUERY_TEMPLATE = "SELECT * FROM chat.users WHERE id=?";

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        ResultSet   resultSet = null;

        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User user) {
        final String QUERY_TEMPLATE_INSERT =
                "INSERT INTO chat.users (login, password)" +
                        " VALUES (?, ?) RETURNING *";
        ResultSet resultSet = null;
        try {
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TEMPLATE_INSERT);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
