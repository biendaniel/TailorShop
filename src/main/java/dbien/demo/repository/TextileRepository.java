package dbien.demo.repository;

import dbien.demo.domain.Textile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TextileRepository extends CrudRepository<Textile, Integer> {

    @Query(value = "SELECT * FROM textile WHERE name = ?1",
            nativeQuery = true)
    Optional<Textile> checkIfTextileExists(String name);

}
