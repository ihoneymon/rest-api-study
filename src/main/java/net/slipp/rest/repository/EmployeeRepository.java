package net.slipp.rest.repository;

import net.slipp.rest.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Employee Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Long, Employee> {

}
