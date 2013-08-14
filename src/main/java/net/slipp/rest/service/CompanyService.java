package net.slipp.rest.service;

import net.slipp.rest.domain.Company;
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
     */
    List<Company> findAll();

    /**
     * 기업 상세정보 조회
     * @param companyId 기업ID
     * @return
     */
    Company findCompanyById(Long companyId);

    void save(Company company);

}
