package ex03;

public interface UserList {
    void addUser(String userName, float amount);
    User getUserByiD(long id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    long getUserCount();
}
