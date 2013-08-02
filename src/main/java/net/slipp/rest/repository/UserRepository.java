package net.slipp.rest.repository;

import net.slipp.rest.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
