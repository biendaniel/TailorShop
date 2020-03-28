package daniel.bien.tailor_shop.repository.product;

import daniel.bien.tailor_shop.model.product.TextileType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextileTypeRepository extends CrudRepository<TextileType, Integer> {
}
