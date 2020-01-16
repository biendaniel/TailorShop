package dbien.demo.repository;

import dbien.demo.domain.Dimensions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DimensionsRepository extends CrudRepository<Dimensions, Integer> {


}
