package net.slipp.rest.controller.api;

import net.slipp.rest.controller.api.form.CompanyForm;
import net.slipp.rest.controller.api.form.TestForm;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.condition.CompanyCondition;
import net.slipp.rest.service.CompanyService;
import net.slipp.rest.support.common.Pagination;
import net.slipp.rest.support.common.Paginations;
import net.slipp.rest.support.exception.SlippException;
import net.slipp.rest.support.view.PageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Inject
    private MessageSourceAccessor messageSourceAccessor;

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
        try {
            Company company = form.createCompany();
            companyService.save(company);
            map.put("company", company);
            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", e.getHttpStatus());
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 기업정보 수정
     * @param company 기존기업정보 {@link Company}
     * @param form 기업정보 수정사항 {@link CompanyForm}
     * @param map
     */
    @RequestMapping(value="/{company}", method=RequestMethod.PUT)
    public void modifyCompany(@PathVariable Company company, @RequestBody CompanyForm form, ModelMap map) {
        try {
            companyService.save(form.bind(company));
            map.put("company", company);
            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", e.getHttpStatus());
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 기업정보 삭제
     * @param company 삭제 기업정보 {@link Company}
     * @param map
     */
    @RequestMapping(value="/{company}", method=RequestMethod.DELETE)
    public void deleteCompany(@PathVariable Company company, ModelMap map) {
        try {
            companyService.delete(company);
            map.put("code", HttpStatus.OK);
        } catch(SlippException e) {
            map.put("code", e.getHttpStatus());
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    @RequestMapping(value="/test-array", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity testJson(TestForm form) {
        logger.debug("TestForm : {}", form);
        return new ResponseEntity<Object>("", HttpStatus.OK);
    }
}