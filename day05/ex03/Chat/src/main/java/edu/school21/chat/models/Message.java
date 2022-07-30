package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long        id;
    private User        author;
    private Chatroom    room;
    private String      messageText;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Chatroom room, String messageText, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.messageText = messageText;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, messageText, dateTime);
    }

    @Override
    public boolean equals(Object obj) {
        Message message;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        message = (Message) obj;
        if (this.id == message.getId()
            && this.author.equals(message.getAuthor())
            && this.room.equals(message.getRoom())
            && this.messageText.equals(message.getMessageText())
            && this.dateTime.equals(message.getDateTime()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Message: {" +
                "\nid: " + this.id +
                ",\nauthor: " + this.author +
                ",\nroom: " + this.room +
                ",\nmessageText: \"" + this.messageText + '\"' +
                ",\ndateTime: " + this.dateTime +
                "\n}";
    }
}
