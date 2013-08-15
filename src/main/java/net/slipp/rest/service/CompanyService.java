package net.slipp.rest.service;

import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.condition.CompanyCondition;
import net.slipp.rest.support.view.PageStatus;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Transactional
public interface CompanyService {
    /**
     * 기업 전체정보 조회
     * @return
     * @param condition
     * @param pageStatus
     */
    Page<Company> findAll(CompanyCondition condition, PageStatus pageStatus);

    /**
     * 기업 상세정보 조회
     * @param companyId 기업ID
     * @return
     */
    Company findCompanyById(Long companyId);

    void save(Company company);

    void delete(Company company);
}
