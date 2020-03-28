package daniel.bien.tailor_shop.repository.product;

import daniel.bien.tailor_shop.model.product.Textile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextileRepository extends CrudRepository<Textile, Integer> {

    @Query(value = "SELECT * FROM textile WHERE name = ?1",
            nativeQuery = true)
    Optional<Textile> checkIfTextileExists(String name);

  @Query(value = "SELECT * FROM textile WHERE textile_type_id = ?1",
           nativeQuery = true)
    Optional<List<Textile>> findTextilesByTextileType(Integer id);

    @Query(value = "SELECT max(id) FROM textile",
            nativeQuery = true)
  Optional<Integer> findLastId();

    @Query(value = "SELECT main_image_id FROM textile where id = ?1",
            nativeQuery = true)
    Optional<Integer> findImageIdByTextileId(Integer id);
}
