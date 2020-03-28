package daniel.bien.tailor_shop.controller.user;

import daniel.bien.tailor_shop.model.user.RoleName;
import daniel.bien.tailor_shop.model.user.User;
import daniel.bien.tailor_shop.repository.order.OrderRepository;
import daniel.bien.tailor_shop.service.order.OrderService;
import daniel.bien.tailor_shop.service.user.EmployeeService;
import daniel.bien.tailor_shop.service.user.UserService;
import daniel.bien.tailor_shop.model.user.Employee;
import daniel.bien.tailor_shop.repository.user.EmployeeRepository;
import daniel.bien.tailor_shop.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin()
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, UserService userService, UserRepository userRepository, OrderRepository orderRepository, OrderService orderService, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return ResponseEntity.ok(employeeService.convertToDTOCollection(employees));
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody User user) {
        if (userService.checkIfUserExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist!");
        }
        User newUser = userService.createUser(user);
        newUser.setRole(RoleName.ROLE_EMPLOYEE);
        Employee newEmployee = prepareNewEmployeeData(newUser);
        userRepository.save(newUser);
        employeeRepository.save(newEmployee);

        return ResponseEntity.status(HttpStatus.OK).body("User was created");
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity getEmployeeOrders(@PathVariable int id) {
        return ResponseEntity.ok(orderService.convertToOrderDTOCollection(orderRepository.getOrdersByEmployeeId(id)));
    }

    private Employee prepareNewEmployeeData(User user) {
        Employee newEmployee = new Employee();
        newEmployee.setUser(user);
        newEmployee.setManager(false);
        return newEmployee;
    }
}
