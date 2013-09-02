package net.slipp.rest.service;

import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.condition.CompanyCondition;
import net.slipp.rest.support.exception.SlippException;
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

    /**
     * 기업정보 저장<br/>
     * 추가 : id 가 빈값 혹은 -1<br/>
     * 수정 : ID가 지정된 경우<br/>
     * @param company {@link Company}
     */
    void save(Company company);

    /**
     * 기업정보 삭제<br/>
     * @param company
     * @throws SlippException 하위부서가 존재할 경우, 소속직원 정보가 있을 경우
     */
    void delete(Company company) throws SlippException ;
}
