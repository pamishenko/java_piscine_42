package edu.school21.chat.repositories;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Optional<Message>   findById(Long id) throws SQLException;
    void                save(Message message) throws NotSavedSubEntityException;

    void update(Message message) throws NotSavedSubEntityException;

    List<Message> findAllMessageByUserId(Long userId);

}
