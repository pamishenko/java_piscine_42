package edu.school21.repositories;

import edu.school21.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private static final String QUERY_TEMPLATE = "SELECT * FROM TEST.USERS WHERE name=?";
    private final Connection connection;

    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public Optional<User> findByName(String name) {
        User user = null;
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement =
                    this.connection.prepareStatement(QUERY_TEMPLATE);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(user);
    }
}
