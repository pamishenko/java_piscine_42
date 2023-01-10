package edu.school21.models;

public class User {
    Long id;
    String login;
    String password;

    public boolean isAuthenticationSuccessStatus() {
        return authenticationSuccessStatus;
    }

    boolean authenticationSuccessStatus;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        authenticationSuccessStatus = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setAuthenticationSuccessStatus(boolean authenticationSuccessStatus) {
        this.authenticationSuccessStatus = authenticationSuccessStatus;
    }
}
