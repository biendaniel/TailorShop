package dbien.demo.repository;

import dbien.demo.domain.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

    @Query(value = "SELECT max(id) FROM image", nativeQuery =
            true)
    Long getLastImageId();
}
