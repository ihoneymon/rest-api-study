package net.slipp.rest.controller.api;

import net.slipp.rest.controller.form.CompanyForm;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.condition.CompanyCondition;
import net.slipp.rest.service.CompanyService;
import net.slipp.rest.support.common.Pagination;
import net.slipp.rest.support.common.Paginations;
import net.slipp.rest.support.mapper.ExtensibleModelMapper;
import net.slipp.rest.support.view.PageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Controller
@RequestMapping("/companies")
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Inject
    private CompanyService companyService;
    @Inject
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public void getCompanies(CompanyCondition condition, PageStatus pageStatus, ModelMap map) {
        /**
         * 페이지로 반환된 기업정보를 화면에서 페이징처리할 수 있도록 {@link Pagination}으로 변환하여 화면에 전달
         */
        Pagination<Company> page = Paginations.pagination(companyService.findAll(condition, pageStatus));
        map.put("companies", page);
    }

    @RequestMapping(value = "/{company}", method = RequestMethod.GET)
    public void getCompany(@PathVariable("company") Company company, ModelMap map) {
        logger.debug("Company : {}", company);
        map.put("company", company);
    }

    /**
     * 기업정보 등록
     *
     * @param form
     * @param map
     */
    @RequestMapping(method = RequestMethod.POST)
    public void createCompany(@RequestBody CompanyForm form, ModelMap map) {
        logger.debug("CompanyForm : {}", form);
        Company company = form.createCompany();
        companyService.save(company);
        logger.debug("Created Company : {}", company);
        map.put("companyId", company);
    }


}