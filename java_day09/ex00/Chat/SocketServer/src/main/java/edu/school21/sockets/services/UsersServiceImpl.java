package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    @Qualifier("usersRepositoryImpl")
    UsersRepositoryImpl usersRepositoryImpl;

//    @Autowired
//    @Qualifier("passwordEncoder")
//    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(String name, String password) {
        User user = new User(name);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(42);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
    @Override
    public boolean signUp(String username, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(42);

        Optional<User> userOptional = usersRepositoryImpl.findByName(username);
        String userPassword = "";
        if (userOptional.isPresent()){
            userPassword = userOptional.get().getPassword();
        }
        return passwordEncoder.matches(userPassword, password);
    }
}
