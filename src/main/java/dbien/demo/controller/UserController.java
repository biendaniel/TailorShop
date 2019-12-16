package dbien.demo.controller;

import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.UserRepository;
import dbien.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping()
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        if (userService.checkIfUserExists(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist!");
        }
        User newUser = userService.createUser(userDTO);
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("User was created");
    }

    @PostMapping("authenticate")
    public ResponseEntity userAuthenticate(@RequestBody UserDTO userDTO) {
        Optional<User> foundUser = userRepository.checkIfUserExists(userDTO.getEmail());

        if (foundUser.isPresent() && passwordEncoder.matches(userDTO.getPassword(), foundUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(foundUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect login or password");
        }
    }

}