package net.slipp.rest.service;

import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.Employee;
import net.slipp.rest.domain.condition.EmployeeCondition;
import net.slipp.rest.support.view.PageStatus;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Transactional
public interface EmployeeService {
    /**
     * 직원 목록
     * @param condition {@link EmployeeCondition} 직원검색조건
     * @param pageStatus {@link PageStatus} 페이징 처리를 위한 조건
     * @return
     */
    @Transactional(readOnly = true)
    Page<Employee> getEmployee(EmployeeCondition condition, PageStatus pageStatus);

    /**
     * 직원생성
     * @param employee
     * @return
     */
    Employee save(Employee employee);

    /**
     * 기업의 직원목록 가져오기
     * @param company {@link Company}
     * @param condition {@link EmployeeCondition}
     * @param pageStatus {@link PageStatus}
     * @return
     */
    Page<Employee> getEmployeesOfCompany(Company company, EmployeeCondition condition, PageStatus pageStatus);

    /**
     * 부서의 직원목록 가져오기
     * @param company {@link Company}
     * @param department {@link Department}
     * @param condition {@link EmployeeCondition}
     * @param pageStatus {@link PageStatus}
     * @return
     */
    Page<Employee> getEmployeeOfDepartment(Company company, Department department, EmployeeCondition condition, PageStatus pageStatus);
}
