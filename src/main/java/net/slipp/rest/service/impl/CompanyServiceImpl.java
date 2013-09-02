package net.slipp.rest.service.impl;

import javax.inject.Inject;

import com.mysema.query.BooleanBuilder;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.QCompany;
import net.slipp.rest.domain.condition.CompanyCondition;
import net.slipp.rest.repository.CompanyRepository;
import net.slipp.rest.service.CompanyService;

import net.slipp.rest.support.view.PageStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Company Service
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Inject
    private CompanyRepository companyRepository;

    @Override
    public Page<Company> findAll(CompanyCondition condition, PageStatus pageStatus) {
        BooleanBuilder builder = new BooleanBuilder();
        QCompany qCompany = QCompany.company;

        if(StringUtils.hasText(condition.getName())) {
            builder.and(qCompany.name.contains(condition.getName()));
        }

        if(StringUtils.hasText(condition.getTel())) {
            builder.and(qCompany.tel.contains(condition.getTel()));
        }

        if(StringUtils.hasText(condition.getAddress())) {
            builder.and(qCompany.address.contains(condition.getAddress()));
        }

        if(pageStatus.getSort() == null) {
            pageStatus.addSort(new Sort(Sort.Direction.ASC, "name"));
        }

        return companyRepository.findAll(builder, pageStatus);
    }

    @Override
    public Company findCompanyById(Long companyId) {
        return companyRepository.findCompanyById(companyId);
    }

    @Override
    public void save(Company company) {
        //TODO Exception
        companyRepository.save(company);
    }

    @Override
    public void delete(Company company) {
        //TODO Exception
        // 부서정보가 있다면 삭제 불가!
        // 직원정보가 있다면 삭제 불가!
        companyRepository.delete(company);
    }

}
