package ex03;

class UserNotFoundException extends ClassNotFoundException{
    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return "User not found";
    }
}

public class UsersArrayList implements UserList {
    User[] arrayUsers = new User[10];
    int count = 0;

    private void arrayUserCopy() {
        User[] newArrayUsers;

        newArrayUsers = new User[count * 2];
        for (int i = 0; i < count; i++) {
            newArrayUsers[i] = arrayUsers[i];
        }
        arrayUsers = newArrayUsers;
    }

    @Override
    public void addUser(String userName, float amount) {
        if (arrayUsers.length == count) {
            arrayUserCopy();
        }
        arrayUsers[count] = new User(userName, amount);
        count++;
    }

    @Override
    public User getUserByiD(long id) throws UserNotFoundException {
        for (User user: arrayUsers) {
            if (user.getUserId() == id)
                return user;
        }
        return null;
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException {
        if (index >= count || index < 0)
            throw new UserNotFoundException();
        return arrayUsers[index];
    }

    @Override
    public long getUserCount() {
        return count;
    }
}
