package daniel.bien.tailor_shop.repository.product;

import daniel.bien.tailor_shop.model.product.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Integer> {
}
