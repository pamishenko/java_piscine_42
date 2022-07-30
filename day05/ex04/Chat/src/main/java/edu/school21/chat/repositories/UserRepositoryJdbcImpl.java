package edu.school21.chat.repositories;


import edu.school21.chat.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository{
    private final Connection    connection;
    private static final String QUERY_TEMPLATE_SELECT = "SELECT * FROM chat.users WHERE id=?";

    private static final String QUERY_TEMPLATE_SELECT_ALL = "SELECT * FROM chat.users";

    private static final String QUERY_TEMPLATE_INSERT =
            "INSERT INTO chat.users (login, password)" +
                    " VALUES (?, ?) RETURNING *";
    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        ResultSet   resultSet = null;
        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE_SELECT);
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

    @Override
    public List<User> findAll() {
        List<User> users = new List<>();
        ResultSet   resultSet = null;
        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                users = add(java.util.Collections.singletonList(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        new ArrayList<>(),
                        new ArrayList<>()))
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(user);    }
}
