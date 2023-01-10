package ex03;

public interface UserList {
    void addUser(String userName, int amount);
    User getUserByiD(int id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    long getUserCount();
}
