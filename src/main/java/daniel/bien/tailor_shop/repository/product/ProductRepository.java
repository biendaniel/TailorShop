package daniel.bien.tailor_shop.repository.product;

import daniel.bien.tailor_shop.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {


    @Query(value = "select p.* from product p, customer c where c.id = ?1 AND c.id = p.customer_id ;",
            nativeQuery = true)
    List<Product> getProductsByCustomerId(int customerId);
}
