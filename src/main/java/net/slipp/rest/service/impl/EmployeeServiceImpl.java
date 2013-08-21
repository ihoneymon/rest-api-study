package net.slipp.rest.service.impl;

import com.mysema.query.BooleanBuilder;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Employee;
import net.slipp.rest.domain.QEmployee;
import net.slipp.rest.domain.condition.EmployeeCondition;
import net.slipp.rest.repository.EmployeeRepository;
import net.slipp.rest.service.EmployeeService;

import net.slipp.rest.support.view.PageStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getEmployee(EmployeeCondition condition, PageStatus pageStatus) {
        QEmployee qEmployee = QEmployee.employee;
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.getName())) {
            builder.and(qEmployee.name.contains(condition.getName()));
        }

        if (StringUtils.hasText(condition.getEmail())) {
            builder.and(qEmployee.email.contains(condition.getEmail()));
        }

        if (StringUtils.hasText(condition.getNickName())) {
            builder.and(qEmployee.nickName.contains(condition.getNickName()));
        }

        if (condition.getCompany() != null) {
            builder.and(qEmployee.company.eq(condition.getCompany()));
        }

        if (condition.getDepartment() != null) {
            builder.and(qEmployee.departments.contains(condition.getDepartment()));
        }

        if (pageStatus.getSort() == null) {
            pageStatus.addSort(new Sort(Sort.Direction.ASC, "name"));
        }

        return employeeRepository.findAll(builder, pageStatus);
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Page<Employee> getEmployeesOfCompany(Company company, EmployeeCondition condition, PageStatus pageStatus) {
        QEmployee qEmployee = QEmployee.employee;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qEmployee.company.eq(company));

        if (StringUtils.hasText(condition.getName())) {
            builder.and(qEmployee.name.contains(condition.getName()));
        }

        if (StringUtils.hasText(condition.getEmail())) {
            builder.and(qEmployee.email.contains(condition.getEmail()));
        }

        if (StringUtils.hasText(condition.getNickName())) {
            builder.and(qEmployee.nickName.contains(condition.getNickName()));
        }

        if (condition.getDepartment() != null) {
            builder.and(qEmployee.departments.contains(condition.getDepartment()));
        }

        if (pageStatus.getSort() == null) {
            pageStatus.addSort(new Sort(Sort.Direction.ASC, "name"));
        }

        return employeeRepository.findAll(builder, pageStatus);
    }
}
