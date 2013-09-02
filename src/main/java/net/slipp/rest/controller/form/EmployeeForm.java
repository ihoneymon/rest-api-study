package net.slipp.rest.controller.form;

import com.google.common.collect.Sets;
import lombok.Data;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.Employee;
import org.springframework.beans.BeanUtils;

import java.util.Set;

/**
 * 직원정보 입력폼
 * User: ihoneymon
 * Date: 13. 8. 16.
 * Time: 오후 7:49
 */
@Data
public class EmployeeForm {
    private String name;
    private String email;
    private String nickName;

    public Employee createEmployee(Company company) {
        Employee employee = new Employee(company, name, email);
        BeanUtils.copyProperties(this, employee);
        return employee;
    }

    public Employee bind(Employee target) {
        BeanUtils.copyProperties(this, target);
        return target;
    }
}
