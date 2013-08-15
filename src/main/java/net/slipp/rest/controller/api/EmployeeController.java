package net.slipp.rest.controller.api;

import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Controller
public class EmployeeController {

    @RequestMapping(value = "/companies/{company}/departments/{departments}/employees", method = RequestMethod.GET)
    public void getEmployeesOfDepartment(@PathVariable Company company, @PathVariable Department department, ModelMap map) {

    }

    @RequestMapping(value = "/companies/{company}/employees", method = RequestMethod.GET)
    public void getEmployeesOfCompany(@PathVariable Company company, ModelMap map) {

    }
}
