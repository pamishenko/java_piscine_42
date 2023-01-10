package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository{
    private final Connection                    connection;
    private final UserRepositoryJdbcImpl        userRepository;
    private final ChatroomRepositoryJdbcImpl    chatroomRepository;
    private static final String                 QUERY_TEMPLATE =
            "SELECT * FROM chat.messages WHERE id=?";

    public MessageRepositoryJdbcImpl(Connection connection,
                                     UserRepositoryJdbcImpl userRepository,
                                     ChatroomRepositoryJdbcImpl chatroomRepository) {
        this.connection = connection;
        this.userRepository = userRepository;
        this.chatroomRepository = chatroomRepository;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Message     message = null;
        ResultSet   resultSet = null;

        try {
            PreparedStatement preparedStatement = this.connection.
                                        prepareStatement(QUERY_TEMPLATE);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message = new Message(
                        resultSet.getLong("id"),
                        userRepository.findById(resultSet.getLong("author")).orElse(null),
                        chatroomRepository.findById(resultSet.getLong("room")).orElse(null),
                        resultSet.getString("message_text"),
                        resultSet.getTimestamp("date_time").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(message);
    }
}
