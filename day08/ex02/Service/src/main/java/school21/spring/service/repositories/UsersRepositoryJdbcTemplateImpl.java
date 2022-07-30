package school21.spring.service.repositories;

import school21.spring.service.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Transactional
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    JdbcTemplate template;
    private final String QUERY_TEMPLATE_FIND_BY_ID = "SELECT * FROM users.users WHERE id=?";
    private final String QUERY_TEMPLATE_FIND_ALL = "SELECT * FROM users.users";
    private final String QUERY_TEMPLATE_INSERT = "INSERT INTO users.users (email, password) VALUES (?, ?)";
    private final String QUERY_TEMPLATE_UPDATE = "UPDATE users.users SET email=?, password=? WHERE id=?";
    private final String QUERY_TEMPLATE_DELETE = "DELETE FROM users.users WHERE id=?";
    private final String QUERY_TEMPLATE_FIND_BY_EMAIL = "SELECT * FROM users.users WHERE email=?";

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
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
        template.update(QUERY_TEMPLATE_INSERT, entity.getEmail(), entity.getPassword());
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
    public Optional<User> findByEmail(String email) {
        List<User> user = template.query(QUERY_TEMPLATE_FIND_BY_EMAIL, userRowMapper, email);
        if (user.size() == 0)
            return Optional.empty();
        return Optional.ofNullable(user.get(0));
    }
}
