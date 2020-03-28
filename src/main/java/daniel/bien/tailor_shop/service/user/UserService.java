package daniel.bien.tailor_shop.service.user;

import daniel.bien.tailor_shop.model.user.User;
import daniel.bien.tailor_shop.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User userDTO) {

        User user = new User();
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setPassword(userDTO.getPassword());
        return user;
    }

    public boolean checkIfUserExists(String email) {
        return userRepository.checkIfUserExists(email).isPresent();
    }

    public boolean checkAuthenticationData(User user, User foundUser) {
        return (passwordEncoder.matches(user.getPassword(), foundUser.getPassword()));
    }
}
