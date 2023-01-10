package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final Connection                connection;
    private final UserRepositoryJdbcImpl    userRepository;
    private static final String             QUERY_TEMPLATE = "SELECT * FROM chat.rooms WHERE id=?";

    public ChatroomRepositoryJdbcImpl(Connection connection, UserRepositoryJdbcImpl userRepository) {
        this.connection = connection;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Chatroom> findById(Long id) throws SQLException {
        Chatroom    chatroom = null;
        ResultSet   resultSet = null;

        try {
            PreparedStatement query = this.connection.prepareStatement(QUERY_TEMPLATE);
            query.setLong(1, id);
            resultSet = query.executeQuery();
            if (resultSet.next()) {
                chatroom = new Chatroom(
                                        resultSet.getLong("id"),
                                        resultSet.getString("name"),
                                        userRepository.findById(resultSet.getLong("owner")).orElse(null),
                                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(chatroom);
    }

}
