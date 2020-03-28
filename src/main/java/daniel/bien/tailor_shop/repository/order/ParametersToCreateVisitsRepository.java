package daniel.bien.tailor_shop.repository.order;


import daniel.bien.tailor_shop.model.order.ParametersToCreateVisits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersToCreateVisitsRepository extends CrudRepository<ParametersToCreateVisits, Integer> {
}
