package dbien.demo.service;

import dbien.demo.domain.Customer;
import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final UserService userService;

    public CustomerService(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public Customer createCustomer(UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        Customer customer = new Customer();
        customer.setUser(user);
        return customer;
    }

    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
