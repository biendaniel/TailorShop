package daniel.bien.tailor_shop.repository.product;

import daniel.bien.tailor_shop.model.product.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

    @Query(value = "SELECT max(id) FROM image", nativeQuery =
            true)
    Long getLastImageId();
}
