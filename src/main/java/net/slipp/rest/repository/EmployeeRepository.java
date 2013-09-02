package net.slipp.rest.repository;

import net.slipp.rest.domain.Employee;
import net.slipp.rest.repository.querypredicate.JPQLQueryPredicateExecutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Employee Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, QueryDslPredicateExecutor<Employee> {

}
