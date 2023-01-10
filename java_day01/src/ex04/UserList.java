package ex04;

public interface UserList {
    void addUser(User user);
    User getUserByiD(long id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    long getUserCount();
}
