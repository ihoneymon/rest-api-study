package net.slipp.rest.repository;

import net.slipp.rest.domain.User;
import net.slipp.rest.repository.querypredicate.JPQLQueryPredicateExecutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
    User findUserByUsername(String username);
}
