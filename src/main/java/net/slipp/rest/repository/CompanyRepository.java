package net.slipp.rest.repository;

import net.slipp.rest.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Company Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{
    Company findCompanyById(Long companyId);
}
