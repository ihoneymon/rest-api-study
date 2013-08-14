package net.slipp.rest.controller.view;

import org.springframework.stereotype.Controller;
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
    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value="/companies", method = RequestMethod.GET)
    public String viewCompanies() {
        return "companies";
    }

    @RequestMapping(value="/departments", method = RequestMethod.GET)
    public String viewDepartments() {
        return "departments";
    }

    @RequestMapping(value="/employees", method = RequestMethod.GET)
    public String viewEmployees() {
        return "employees";
    }
}
