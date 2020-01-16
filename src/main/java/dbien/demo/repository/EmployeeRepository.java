package dbien.demo.repository;

import dbien.demo.domain.Employee;
import dbien.demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query(value = "SELECT id FROM Employee WHERE user_id = ?1",
            nativeQuery = true)
    int getEmployeeIdByUserId(int userId);

}
