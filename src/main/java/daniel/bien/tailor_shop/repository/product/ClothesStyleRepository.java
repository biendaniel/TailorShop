package daniel.bien.tailor_shop.repository.product;

import daniel.bien.tailor_shop.model.product.ClothesStyle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesStyleRepository extends CrudRepository<ClothesStyle, Integer> {

    @Query(value = "SELECT max(id) FROM clothes_style",
            nativeQuery = true)
    Optional<Integer> findLastId();

    @Query(value = "SELECT main_image_id FROM clothes_style where id = ?1",
            nativeQuery = true)
    Optional<Integer> findImageIdByClothesStyleId(Integer id);

    @Query(value = "SELECT * from clothes_style where product_type_id = ?1", nativeQuery = true)
    List<ClothesStyle> findClothesStyleByProductTypes(int id);

}
