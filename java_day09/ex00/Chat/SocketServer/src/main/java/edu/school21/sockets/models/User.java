package edu.school21.sockets.models;

public class User {
    private Long    id;
    private String name;
    private String  password;

    public User() {
        this.name = "";
        this.password = "";
    }

    public User(String name) {
        this.name = name;
        this.password = "";
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.password = "";
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id - " + id + ", " +
                "name - " + name + ", " +
                "password - " + (password.length() > 0) +"}";
    }
}
