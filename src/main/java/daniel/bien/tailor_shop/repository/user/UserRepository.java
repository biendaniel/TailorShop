package daniel.bien.tailor_shop.repository.user;

import daniel.bien.tailor_shop.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM User WHERE email = ?1",
    nativeQuery = true)
    Optional<User> checkIfUserExists(String email);
}
