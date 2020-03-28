package daniel.bien.tailor_shop.repository.order;

import daniel.bien.tailor_shop.model.order.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(value = "select o.* from `order` o where o.customer_id = ?1",
            nativeQuery = true)
    List<Order> getOrdersByCustomerId(int customerId);

    @Query(value = "select * from `order` where status = ?1",
            nativeQuery = true)
    List<Order> getOrdersByStatus(String status);

    @Query(value = "select o.* from `order` o where o.employee_id = ?1",
            nativeQuery = true)
    List<Order> getOrdersByEmployeeId(int employeeId);

}
