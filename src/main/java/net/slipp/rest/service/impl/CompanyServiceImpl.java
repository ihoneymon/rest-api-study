package net.slipp.rest.service.impl;

import javax.inject.Inject;

import net.slipp.rest.domain.Company;
import net.slipp.rest.repository.CompanyRepository;
import net.slipp.rest.service.CompanyService;

import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long companyId) {
        return companyRepository.findCompanyById(companyId);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

}
