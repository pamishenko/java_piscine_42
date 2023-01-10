package school21.spring.service.models;

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

    public void setEmail(String email) {
        this.email = email;
    }


    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public User(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id: " + id + ", email: " + email + "}";
    }
}
