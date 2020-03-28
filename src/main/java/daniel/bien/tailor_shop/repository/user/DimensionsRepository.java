package daniel.bien.tailor_shop.repository.user;

import daniel.bien.tailor_shop.model.user.Dimensions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DimensionsRepository extends CrudRepository<Dimensions, Integer> {


}
