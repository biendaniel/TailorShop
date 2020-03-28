package daniel.bien.tailor_shop.service.user;

import daniel.bien.tailor_shop.model.user.Employee;
import daniel.bien.tailor_shop.dto.EmployeeDTO;
import daniel.bien.tailor_shop.service.order.OrderService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {

    private final OrderService orderService;

    public EmployeeService(OrderService orderService) {
        this.orderService = orderService;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setUser(employee.getUser());
        employeeDTO.setId(employee.getId());
        employeeDTO.setOrders(orderService.convertToOrderDTOCollection(employee.getOrders()));
        return employeeDTO;
    }

    public List<EmployeeDTO> convertToDTOCollection(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new LinkedList<>();
        employees.forEach(employee -> {
            employeeDTOs.add(convertToDTO(employee));
        });
        return employeeDTOs;
    }
}
