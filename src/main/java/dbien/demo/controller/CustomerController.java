package dbien.demo.controller;

import dbien.demo.domain.Customer;
import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.CustomerRepository;
import dbien.demo.repository.UserRepository;
import dbien.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/customers")
@RestController
public class CustomerController {


    private final UserService userService;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerController(UserService userService, CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable(value = "id") Integer id) {
        return customerRepository.findById(id);
    }

    @PostMapping()
    public ResponseEntity addCustomer(@RequestBody UserDTO userDTO) {
        if(userService.checkIfUserExists(userDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist!");
        }
        User newUser = userService.createUser(userDTO);
        userRepository.save(newUser);

        Customer newCustomer = new Customer();
        newCustomer.setUser(newUser);

        customerRepository.save(newCustomer);

        return ResponseEntity.status(HttpStatus.OK).body("User was created");
    }

    @GetMapping
    public ResponseEntity getCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }


}
