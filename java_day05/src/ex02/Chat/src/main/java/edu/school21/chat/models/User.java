package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class    User {
    private Long                id;
    private String              login;
    private String              password;
    private ArrayList<Chatroom> createRooms;
    private ArrayList<Chatroom> socialRooms;

    public User(Long id, String login, String password, ArrayList<Chatroom> createRooms, ArrayList<Chatroom> socialRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createRooms = createRooms;
        this.socialRooms = socialRooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Chatroom> getCreateRooms() {
        return createRooms;
    }

    public void setCreateRooms(ArrayList<Chatroom> createRooms) {
        this.createRooms = createRooms;
    }

    public ArrayList<Chatroom> getSocialRooms() {
        return socialRooms;
    }

    public void setSocialRooms(ArrayList<Chatroom> socialRooms) {
        this.socialRooms = socialRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createRooms, socialRooms);
    }

    @Override
    public boolean equals(Object obj) {
        User user;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        user = (User) obj;
        if (this.id == user.getId()
            && this.login.equals(user.getLogin())
            && this.password.equals(user.getPassword())
            && this.createRooms.equals(user.getCreateRooms())
            && this.socialRooms.equals(user.getSocialRooms()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "User: {" +
                "id: " + this.id +
                ", login: " + this.login +
                ", password: ******" +
                ", create rooms: " + this.createRooms.toString() +
                ", social rooms: " + this.socialRooms.toString();
    }
}
