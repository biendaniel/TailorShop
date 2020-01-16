package dbien.demo.repository;

import dbien.demo.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query(value = "SELECT id FROM Customer WHERE user_id = ?1",
            nativeQuery = true)
    int getCustomerIdByUserId(int userId);

}
