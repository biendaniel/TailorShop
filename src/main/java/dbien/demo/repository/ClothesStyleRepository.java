package dbien.demo.repository;

import dbien.demo.domain.ClothesStyle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesStyleRepository extends CrudRepository<ClothesStyle, Integer> {
}
