package net.slipp.rest.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: ihoneymon
 * Date: 13. 8. 2.
 * Time: 오후 7:04
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class RootController {
    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
    public String getIndex() {
        logger.debug("Get Index!");
        return "index";
    }

    @RequestMapping(value="/companies", method = RequestMethod.GET)
    public String viewCompanies() {
        return "companies";
    }

    @RequestMapping(value="/companies/{companyId}", method=RequestMethod.GET)
    public String viewCompanyDetail(@PathVariable("companyId") Long companyId, ModelMap map) {
        map.put("companyId", companyId);
        return "company-detail";
    }

    @RequestMapping(value={"/companies/{companyId}/departments"}, method = RequestMethod.GET)
    public String viewDepartments(@PathVariable("companyId") Long companyId, ModelMap map) {
        map.put("companyId", companyId);
        return "departments";
    }

    @RequestMapping(value="/employees", method = RequestMethod.GET)
    public String viewEmployees() {
        return "employees";
    }

    @RequestMapping(value="/companies/{companyId}/departments/{departmentId}/employees", method = RequestMethod.GET)
    public String viewEmployeesOfDepartment(@PathVariable Long companyId, @PathVariable Long departmentId, ModelMap map) {
        map.put("companyId", companyId);
        map.put("departmentId", departmentId);
        return "employees";
    }

    @RequestMapping(value={"/companies/{companyId}/employees"}, method = RequestMethod.GET)
    public String viewEmployeesOfCompany(@PathVariable Long companyId, ModelMap map) {
        map.put("companyId", companyId);
        return "employees";
    }
}
