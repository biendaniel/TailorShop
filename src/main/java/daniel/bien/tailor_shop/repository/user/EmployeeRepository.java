package daniel.bien.tailor_shop.repository.user;

import daniel.bien.tailor_shop.model.order.Order;
import daniel.bien.tailor_shop.model.user.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query(value = "SELECT id FROM Employee WHERE user_id = ?1",
            nativeQuery = true)
    int getEmployeeIdByUserId(int userId);

    @Query(value = "select o.* from `order` o where o.employee_id = ?1",
            nativeQuery = true)
    List<Order> getOrdersByCustomerId(int customerId);


}
