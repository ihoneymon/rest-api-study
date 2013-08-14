package net.slipp.rest.controller.api;

import javax.inject.Inject;

import net.slipp.rest.controller.form.CompanyForm;
import net.slipp.rest.domain.Company;
import net.slipp.rest.service.CompanyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public void getCompanies(ModelMap map) {
        map.put("companies", companyService.findAll());
    }

    @RequestMapping(value = "/{company}", method = RequestMethod.GET)
    public void getCompany(@PathVariable("company") Company company, ModelMap map) {
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
        map.put("company", company);
    }

    @RequestMapping(value="/password-encoder/{targetKeyword}", method = RequestMethod.GET)
    public void testEncrypt(@PathVariable("targetKeyword")String targetKeyword, ModelMap map) {
        String encodedTargetKeyword = passwordEncoder.encode(targetKeyword);
        map.put("result", encodedTargetKeyword);
    }
}
