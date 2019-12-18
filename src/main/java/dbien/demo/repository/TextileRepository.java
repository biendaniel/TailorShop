package dbien.demo.repository;

import dbien.demo.domain.Textile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextileRepository extends CrudRepository<Textile, Integer> {
}
