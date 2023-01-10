package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {
    JdbcTemplate template;
    private final String QUERY_TEMPLATE_FIND_BY_ID = "SELECT * FROM users.users WHERE id=?";
    private final String QUERY_TEMPLATE_FIND_ALL = "SELECT * FROM users.users";
    private final String QUERY_TEMPLATE_INSERT = "INSERT INTO users.users (name, password) VALUES (?, ?)";
    private final String QUERY_TEMPLATE_UPDATE = "UPDATE users.users SET name=? WHERE id=?";
    private final String QUERY_TEMPLATE_DELETE = "DELETE FROM users.users WHERE id=?";
    private final String QUERY_TEMPLATE_FIND_BY_EMAIL = "SELECT * FROM users.users WHERE name=?";

    public UsersRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    RowMapper<User> userRowMapper = (preparedStatement, rowNum) ->
            new User(
                    preparedStatement.getLong("id"),
                    preparedStatement.getString("email")
            );

    @Override
    public Optional<User> findById(Long id) {
        List<User> user = template.query(QUERY_TEMPLATE_FIND_BY_ID, userRowMapper, id);
        if (user.size() == 0)
            return Optional.empty();
        return Optional.ofNullable(user.get(0));
    }

    @Override
    public List<User> findAll() {
        return template.query(QUERY_TEMPLATE_FIND_ALL, userRowMapper);
    }

    @Override
    public void save(User entity) {
        template.update(QUERY_TEMPLATE_INSERT, entity.getName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        template.update(QUERY_TEMPLATE_UPDATE, userRowMapper, entity);
    }

    @Override
    public void delete(Long id) {
        template.update(QUERY_TEMPLATE_DELETE, id);
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> user = template.query(QUERY_TEMPLATE_FIND_BY_EMAIL, userRowMapper, name);
        if (user.size() == 0)
            return Optional.empty();
        return Optional.ofNullable(user.get(0));
    }
}
