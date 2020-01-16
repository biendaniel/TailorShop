package dbien.demo.controller;

import dbien.demo.configuration.AuthenticationResponse;
import dbien.demo.configuration.JwtTokenService;
import dbien.demo.domain.RoleName;
import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.CustomerRepository;
import dbien.demo.repository.EmployeeRepository;
import dbien.demo.repository.UserRepository;
import dbien.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserControllerUSUNAC { //TODO to tez chyba bedzie trzeba usunac - wiekszosc metod przeniesc do servisu, a inne podzielic na customerController i employeeControlelr

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public UserControllerUSUNAC(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }


    @PostMapping()
    public ResponseEntity addUser(@RequestBody User userDTO) {
        if (userService.checkIfUserExists(userDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist!");
        }
        User newUser = userService.createUser(userDTO);
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("User was created");
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    private AuthenticationResponse fillAuthenticationResponse(User user) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        String jwt = jwtTokenService.generateToken(user);
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setUserEmail(user.getEmail());
        authenticationResponse.setUserId(user.getId());
        if (user.getRole().equals(RoleName.ROLE_CUSTOMER)) {
            authenticationResponse.setCustomerId(customerRepository.getCustomerIdByUserId(user.getId()));
        } else {
            authenticationResponse.setEmployeeId(employeeRepository.getEmployeeIdByUserId(user.getId()));
        }
        return authenticationResponse;
    }

    @PostMapping("/authenticate")
    public ResponseEntity userAuthenticate(@RequestBody User user) {
        Optional<User> foundUser = userRepository.checkIfUserExists(user.getEmail());
        if (foundUser.isPresent() && userService.checkAuthenticationData(user, foundUser.get())) {
            return ResponseEntity.status(HttpStatus.OK).body(fillAuthenticationResponse(foundUser.get()));
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nieprawidłowy e-mail lub hasło");

    }


}