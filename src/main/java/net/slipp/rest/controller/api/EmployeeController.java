package net.slipp.rest.controller.api;

import net.slipp.rest.controller.form.EmployeeForm;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.Employee;
import net.slipp.rest.domain.condition.EmployeeCondition;
import net.slipp.rest.service.EmployeeService;
import net.slipp.rest.support.common.Pagination;
import net.slipp.rest.support.common.Paginations;
import net.slipp.rest.support.exception.SlippException;
import net.slipp.rest.support.view.PageStatus;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Controller
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;
    @Inject
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * 부서별 직원목록
     * @param company {@link Company}
     * @param department {@link Department}
     * @param condition {@link EmployeeCondition}
     * @param pageStatus {@link PageStatus}
     * @param map
     */
    @RequestMapping(value = "/companies/{company}/departments/{department}/employees", method = RequestMethod.GET)
    public void getEmployeesOfDepartment(@PathVariable Company company, @PathVariable Department department, EmployeeCondition condition, PageStatus pageStatus, ModelMap map) {
        Pagination<Employee> page = Paginations.pagination(employeeService.getEmployee(condition, pageStatus));
        map.put("employees", page);
    }

    /**
     * 직원에게 부서정보 추가
     * @param company {@link Company}
     * @param department {@link Department}
     * @param employee {@link Employee}
     * @param map
     */
    @RequestMapping(value = "/companies/{company}/departments/{department}/employees/{employee}", method = RequestMethod.PUT)
    public void addEmployeeOfDepartment(@PathVariable Company company, @PathVariable Department department, @PathVariable Employee employee, ModelMap map) {
        try {
            if(!employee.getCompany().equals(company)) {
                throw new SlippException("system.exception.user.company");
            }

            employee.addDepartment(department);
            employeeService.save(employee);
            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 직원에게 부서 추가
     * @param company
     * @param department
     * @param employee
     * @param map
     */
    @RequestMapping(value = "/companies/{company}/departments/{department}/employees/{employee}", method = RequestMethod.DELETE)
    public void removeEmployeeOfDepartment(@PathVariable Company company, @PathVariable Department department, @PathVariable Employee employee, ModelMap map) {
        try {
            if(!employee.getCompany().equals(company)) {
                throw new SlippException("system.exception.user.company");
            }

            employee.removeDepartment(department);
            employeeService.save(employee);
            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 직원목록
     * @param company
     * @param condition
     * @param pageStatus
     * @param map
     */
    @RequestMapping(value = "/companies/{company}/employees", method = RequestMethod.GET)
    public void getEmployeesOfCompany(@PathVariable Company company, EmployeeCondition condition, PageStatus pageStatus, ModelMap map) {
        Page<Employee> page = employeeService.getEmployeesOfCompany(company, condition, pageStatus);
        map.put("employees", Paginations.pagination(page));
    }

    /**
     * 직원 생성
     * @param company
     * @param form
     * @param map
     */
    @RequestMapping(value="/companies/{company}/employees", method=RequestMethod.POST)
    public void saveEmployee(@PathVariable Company company, @RequestBody EmployeeForm form, ModelMap map) {
        try {
            Employee employee = form.createEmployee(company);
            employeeService.save(employee);

            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 직원 수정
     * @param company
     * @param employee
     * @param form
     * @param map
     */
    @RequestMapping(value="/companies/{company}/employees/{employee}", method=RequestMethod.PUT)
    public void updateEmployee(@PathVariable Company company, @PathVariable Employee employee, @RequestBody EmployeeForm form, ModelMap map) {
        try {
            employeeService.save(form.bind(employee));
            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 직원 삭제
     * @param company
     * @param form
     * @param map
     */
    @RequestMapping(value="/companies/{company}/employees", method=RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable Company company, @RequestBody EmployeeForm form, ModelMap map) {
        try {
            Employee employee = form.createEmployee(company);
            employeeService.save(employee);

            map.put("code", HttpStatus.OK);
        } catch (SlippException e) {
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }
}
