package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class Chatroom {
    private Long                id;
    private String              name;
    private User                owner;
    private ArrayList<Message>  messages;

    public Chatroom(long id, String name, User owner, ArrayList<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messages);
    }

    @Override
    public boolean equals(Object obj) {
        Chatroom room;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        room = (Chatroom) obj;
        if (this.id == room.getId()
            && this.name.equals(room.getName())
            && this.owner.equals(room.getOwner())
            && this.messages.equals(room.getMessages()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id: " + id +
                ", name: " + name +
                ", owner: " + owner +
                ", messages: " + messages +
                "}";
    }
}
