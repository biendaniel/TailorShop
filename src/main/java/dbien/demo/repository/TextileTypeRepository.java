package dbien.demo.repository;

import dbien.demo.domain.TextileType;
import org.springframework.data.repository.CrudRepository;

public interface TextileTypeRepository extends CrudRepository<TextileType, Integer> {
}
