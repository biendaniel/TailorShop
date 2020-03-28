package daniel.bien.tailor_shop.controller.order;

import daniel.bien.tailor_shop.model.order.Order;
import daniel.bien.tailor_shop.model.order.OrderStatusName;
import daniel.bien.tailor_shop.repository.order.OrderRepository;
import daniel.bien.tailor_shop.repository.user.EmployeeRepository;
import daniel.bien.tailor_shop.service.order.OrderService;
import daniel.bien.tailor_shop.model.user.Employee;
import daniel.bien.tailor_shop.dto.PreOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin()
public class OrderController {

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository, EmployeeRepository employeeRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity getAllOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orderService.convertToOrderDTOCollection(orders));
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody Order order) {
        order.setStatus(OrderStatusName.CREATED);
        orderRepository.save(order);
        return ResponseEntity.ok("Dodano zamówienie");
    }

    @GetMapping("/{status}")
    public ResponseEntity getOrdersByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderRepository.getOrdersByStatus(status.toUpperCase()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity changeStatus(@PathVariable Integer id, @RequestBody String status) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            order.ifPresent(value -> {
                if (status.equals(OrderStatusName.FINISHED.toString())) {
                    orderService.sendEmailAfterFinishOrder(order.get());
                }
                value.setStatus(OrderStatusName.valueOf(status));
                orderRepository.save(value);
            });
            return ResponseEntity.ok("Ustawiono status na" + status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity assignEmployee(@PathVariable Integer id, @RequestBody Integer employeeId) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            order.ifPresent(value -> {
                employee.ifPresent(value::setEmployee);
                value.setStatus(OrderStatusName.ASSIGNED);
                orderRepository.save(value);
            });
            return ResponseEntity.ok("Przypisano pracownika");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/paid")
    public ResponseEntity setASPaid(@PathVariable Integer id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            order.ifPresent(value -> {
                value.setPaid(true);
                orderRepository.save(value);
            });
            return ResponseEntity.ok("Ustawiono status na opłacone");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/totalPrice")
    public ResponseEntity getOrderTotalPrice(@RequestBody PreOrderDTO preOrderDTO) {
        return ResponseEntity.ok(orderService.calculateTotalPrice(preOrderDTO.getDimensions(), preOrderDTO.getProducts()));
    }

    @PostMapping("/checkPrice")
    public ResponseEntity checkPrice(@RequestBody Order order) {
            return ResponseEntity.ok(orderService.calculatePrice(order));
    }

}
