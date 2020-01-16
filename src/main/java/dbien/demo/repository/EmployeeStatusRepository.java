package dbien.demo.repository;

import dbien.demo.domain.EmployeeStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeStatusRepository extends CrudRepository<EmployeeStatus, Integer> {
}
