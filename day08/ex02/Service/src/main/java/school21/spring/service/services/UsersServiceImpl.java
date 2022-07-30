package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{
    @Autowired
    @Qualifier("userRepositoryJdbc")
    private UsersRepository usersRepository;
    @Override
    public String signUp(String email) {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        String password = "temp password";
        if (optionalUser.isPresent()) {
            System.err.println("User with this name exists!");
            return null;
        }
        usersRepository.save(new User(email));
        return password;
    }
}
