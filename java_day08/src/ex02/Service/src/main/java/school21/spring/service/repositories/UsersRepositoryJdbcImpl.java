package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import school21.spring.service.models.User;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    private final String QUERY_TEMPLATE_FIND_BY_ID = "SELECT * FROM users.users WHERE id=?";
    private final String QUERY_TEMPLATE_FIND_ALL = "SELECT * FROM users.users";
    private final String QUERY_TEMPLATE_INSERT = "INSERT INTO users.users (email, password) VALUES (?, ?)";
    private final String QUERY_TEMPLATE_UPDATE = "UPDATE users.users SET email=? WHERE id=?";
    private final String QUERY_TEMPLATE_DELETE = "DELETE FROM users.users WHERE id=?";
    private final String QUERY_TEMPLATE_FIND_BY_EMAIL = "SELECT * FROM users.users WHERE email=?";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(QUERY_TEMPLATE_FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = dataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_TEMPLATE_FIND_ALL);
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void save(User entity) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(QUERY_TEMPLATE_INSERT)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        try(PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(QUERY_TEMPLATE_UPDATE)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3,entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(QUERY_TEMPLATE_DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(QUERY_TEMPLATE_FIND_BY_EMAIL)){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(user);
    }

}
