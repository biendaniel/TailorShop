package dbien.demo.repository;

import dbien.demo.domain.TextileType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextileTypeRepository extends CrudRepository<TextileType, Integer> {
}
