package net.slipp.rest.service;

import net.slipp.rest.domain.Company;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
public interface CompanyService {
    Company findCompanyById(Long companyId);
}
