package net.slipp.rest.repository;

import net.slipp.rest.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Department Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Long, Department>{
}
