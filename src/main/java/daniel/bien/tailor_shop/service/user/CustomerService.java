package daniel.bien.tailor_shop.service.user;

import daniel.bien.tailor_shop.dto.CustomerDTO;
import daniel.bien.tailor_shop.model.user.Customer;
import daniel.bien.tailor_shop.model.user.User;
import daniel.bien.tailor_shop.repository.user.CustomerRepository;
import daniel.bien.tailor_shop.service.order.OrderService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final UserService userService;

    private final OrderService orderService;

    public CustomerService(CustomerRepository customerRepository, UserService userService, OrderService orderService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    public Customer createCustomer(User userDTO) {
        User user = userService.createUser(userDTO);
        Customer customer = new Customer();
        customer.setUser(user);
        return customer;
    }

    public CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUser(customer.getUser());
        customerDTO.setId(customer.getId());
        customerDTO.setOrders(orderService.convertToOrderDTOCollection(customer.getOrders()));
        customerDTO.setDimensions(customer.getDimensionsList());
        return customerDTO;
    }

    public List<CustomerDTO> convertToDTOCollection(List<Customer> customers) {
        List<CustomerDTO> customerDTOs = new LinkedList<>();
        customers.forEach(customer -> {
            customerDTOs.add(convertToDTO(customer));
        });
        return customerDTOs;
    }

    public Customer findCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
