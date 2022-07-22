package ex02;

import java.util.ArrayList;

public interface UserList {
    void addUser(String userName, float amount);
    User getUserByiD(int id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    int getUserCount();
}
