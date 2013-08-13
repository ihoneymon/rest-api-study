package net.slipp.rest.repository;

import net.slipp.rest.domain.Company;
import net.slipp.rest.repository.querypredicate.JPQLQueryPredicateExecutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Company Repository
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, QueryDslPredicateExecutor<Company>, JPQLQueryPredicateExecutor<Company> {
    Company findCompanyById(Long companyId);
}
