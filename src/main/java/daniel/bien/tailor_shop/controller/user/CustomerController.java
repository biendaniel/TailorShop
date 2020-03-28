package daniel.bien.tailor_shop.controller.user;

import daniel.bien.tailor_shop.model.order.Order;
import daniel.bien.tailor_shop.model.order.Visit;
import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.model.user.User;
import daniel.bien.tailor_shop.repository.order.OrderRepository;
import daniel.bien.tailor_shop.repository.product.ProductRepository;
import daniel.bien.tailor_shop.repository.user.CustomerRepository;
import daniel.bien.tailor_shop.model.user.Customer;
import daniel.bien.tailor_shop.model.user.Dimensions;
import daniel.bien.tailor_shop.model.user.RoleName;
import daniel.bien.tailor_shop.repository.order.VisitRepository;
import daniel.bien.tailor_shop.repository.user.DimensionsRepository;
import daniel.bien.tailor_shop.repository.user.UserRepository;
import daniel.bien.tailor_shop.service.order.OrderService;
import daniel.bien.tailor_shop.service.product.ProductService;
import daniel.bien.tailor_shop.service.user.CustomerService;
import daniel.bien.tailor_shop.service.user.DimensionsService;
import daniel.bien.tailor_shop.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final VisitRepository visitRepository;
    private final CustomerService customerService;

    public CustomerController(UserService userService, CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DimensionsService dimensionsService, DimensionsRepository dimensionsRepository, ProductRepository productRepository, ProductService productService, OrderRepository orderRepository, OrderService orderService, VisitRepository visitRepository, CustomerService customerService) {
        this.userService = userService;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.dimensionsService = dimensionsService;
        this.dimensionsRepository = dimensionsRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.visitRepository = visitRepository;
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable(value = "id") Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerService.convertToDTO(customerOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity addCustomer(@RequestBody User user) {
        if (userService.checkIfUserExists(user.getEmail())) {
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
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return ResponseEntity.ok(customerService.convertToDTOCollection(customers));
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

    @GetMapping("/{id}/products")
    public ResponseEntity getProducts(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productRepository.getProductsByCustomerId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/products")
    public ResponseEntity addProduct(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Customer> customer = customerRepository.findById(id);
        try {
            if (customer.isPresent()) {
                productService.prepareNewProduct(product);
                productRepository.save(product);
                customer.get().getProducts().add(product);
                customerRepository.save(customer.get());
            }
            return ResponseEntity.ok("Dodano produkt");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}/orders")
    public ResponseEntity getOrders(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(orderService.convertToOrderDTOCollection(orderRepository.getOrdersByCustomerId(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/orders")
    public ResponseEntity addOrder(@PathVariable Integer id, @RequestBody Order order) {
        Optional<Customer> customer = customerRepository.findById(id);
        try {
            if (customer.isPresent()) {
                orderService.prepareNewOrder(order);
                order.setCustomer(customer.get());
                orderRepository.save(order);
                customer.get().getOrders().add(order);
                customerRepository.save(customer.get());
            }
            return ResponseEntity.ok("Dodano produkt");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/visits")
    public ResponseEntity getVisitByCustomerId(@PathVariable Integer id) {
        List<Visit> visits = visitRepository.findIncomingVisitByCustomerId(id);
        return ResponseEntity.ok(visits);
    }

}
