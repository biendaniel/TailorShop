package dbien.demo.controller;

import dbien.demo.domain.Customer;
import dbien.demo.domain.Dimensions;
import dbien.demo.domain.RoleName;
import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.CustomerRepository;
import dbien.demo.repository.DimensionsRepository;
import dbien.demo.repository.UserRepository;
import dbien.demo.service.DimensionsService;
import dbien.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/customers")
@RestController
@CrossOrigin()
public class CustomerController {


    private final UserService userService;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final DimensionsService dimensionsService;
    private final DimensionsRepository dimensionsRepository;

    public CustomerController(UserService userService, CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DimensionsService dimensionsService, DimensionsRepository dimensionsRepository) {
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.dimensionsService = dimensionsService;
        this.dimensionsRepository = dimensionsRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(customerRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody User user) {
        if(userService.checkIfUserExists(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist!");
        }
        User newUser = userService.createUser(user);
        newUser.setRole(RoleName.ROLE_CUSTOMER);
        Customer newCustomer = new Customer();
        newCustomer.setUser(newUser);

        userRepository.save(newUser);
        customerRepository.save(newCustomer);

        return ResponseEntity.status(HttpStatus.OK).body("User was created");
    }

    @GetMapping
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @PostMapping("/{id}/dimensions")
    public ResponseEntity addDimensions(@PathVariable Integer id, @RequestBody Dimensions dimensions) {
        Optional<Customer> customer = customerRepository.findById(id);
        dimensionsRepository.save(dimensions);
        customer.ifPresent(value -> {
            value.getDimensionsList().add(dimensions);
            customerRepository.save(customer.get());
        });

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/dimensions")
    public ResponseEntity getDimensions(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(dimensionsService.getDimensionsListByCustomer(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }


}
