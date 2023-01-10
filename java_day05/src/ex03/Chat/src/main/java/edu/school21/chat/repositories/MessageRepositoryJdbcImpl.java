package edu.school21.chat.repositories;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository{
    private final Connection                    connection;
    private final UserRepositoryJdbcImpl        userRepository;
    private final ChatroomRepositoryJdbcImpl    chatroomRepository;
    private static final String QUERY_TEMPLATE_SELECT =
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
        Message message = null;
        ResultSet   resultSet = null;

        try {
            PreparedStatement preparedStatement = this.connection.
                                        prepareStatement(QUERY_TEMPLATE_SELECT);
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

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        final String QUERY_TEMPLATE_INSERT =
                "INSERT INTO chat.messages (author, room, message_text, date_time)" +
                        " VALUES (?, ?, ?, ?) RETURNING *";
        ResultSet resultSet = null;
        try {
            if (this.userRepository.findById(message.getAuthor().getId()).isPresent()
                    && this.chatroomRepository.findById(message.getRoom().getId()).isPresent()) {
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TEMPLATE_INSERT);
                preparedStatement.setLong(1, message.getAuthor().getId());
                preparedStatement.setLong(2, message.getRoom().getId());
                preparedStatement.setString(3, message.getMessageText());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                message.setId(resultSet.getLong("id"));
                preparedStatement.close();
            } else {
                throw new NotSavedSubEntityException(new ClassNotFoundException());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(Message message) throws NotSavedSubEntityException{
        final String QUERY_TEMPLATE_UPDATE =
                "UPDATE chat.messages set message_text = ?, date_time = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TEMPLATE_UPDATE);
            preparedStatement.setString(1, message.getMessageText());
            preparedStatement.setTimestamp(2,
                    (message.getDateTime() != null) ?
                            Timestamp.valueOf(message.getDateTime()) : null);
            preparedStatement.setLong(3, message.getAuthor().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
