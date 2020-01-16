package dbien.demo.controller;

import dbien.demo.domain.Employee;
import dbien.demo.domain.EmployeeStatus;
import dbien.demo.domain.RoleName;
import dbien.demo.domain.User;
import dbien.demo.dto.UserDTO;
import dbien.demo.repository.EmployeeRepository;
import dbien.demo.repository.EmployeeStatusRepository;
import dbien.demo.repository.UserRepository;
import dbien.demo.service.EmployeeService;
import dbien.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@CrossOrigin()
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final EmployeeStatusRepository employeeStatusRepository;

    public EmployeeController(EmployeeRepository employeeRepository, UserService userService, UserRepository userRepository, EmployeeService employeeService, EmployeeStatusRepository employeeStatusRepository) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.employeeService = employeeService;
        this.employeeStatusRepository = employeeStatusRepository;
    }

    @GetMapping
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.ok(employeeRepository.findAll());
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
        employeeStatusRepository.save(newEmployee.getEmployeeStatus());
        employeeRepository.save(newEmployee);

        return ResponseEntity.status(HttpStatus.OK).body("User was created");
    }

    private Employee prepareNewEmployeeData(User user) {
        Employee newEmployee = new Employee();
        newEmployee.setUser(user);
        newEmployee.setManager(false);
        newEmployee.setEmployeeStatus(employeeService.initNewStatus());
        return newEmployee;
    }

    @GetMapping("/statuses")
    public ResponseEntity getAllEmployeeStatus() {
        return ResponseEntity.ok(employeeStatusRepository.findAll());
    }

    @PostMapping("/statuses")
    public ResponseEntity addEmployeeStatus(@RequestBody EmployeeStatus employeeStatus) {
        employeeStatusRepository.save(employeeStatus);
        return ResponseEntity.ok("dodano");
    }
}
