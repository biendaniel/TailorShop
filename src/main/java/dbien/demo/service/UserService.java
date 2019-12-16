package dbien.demo.service;

import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(UserDTO userDTO) {

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
}
