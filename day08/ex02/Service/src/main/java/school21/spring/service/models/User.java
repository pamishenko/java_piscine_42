package school21.spring.service.models;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class User {
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long    id;
    private String  email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String  password;

    public void setEmail(String email) {
        this.email = email;
    }


    public User(String email) {
        this.email = email;
        this.password = "default";
    }

    public String getEmail() {
        return email;
    }


    public User(Long id, String email) {
        this.id = id;
        this.email = email;
        this.password = "default";
    }

    @Override
    public String toString() {
        return "User{id = " + id +
                " email = " + email +
                " password = " + password + "}";
    }
}
