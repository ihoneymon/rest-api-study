package net.slipp.rest.service.impl;

import com.mysema.query.BooleanBuilder;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.QDepartment;
import net.slipp.rest.domain.condition.DepartmentCondition;
import net.slipp.rest.repository.DepartmentRepository;
import net.slipp.rest.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    @Inject
    private DepartmentRepository departmentRepository;

    @Override
    public Page<Department> getDepartments(Company company, DepartmentCondition condition, PageStatus pageStatus) {
        BooleanBuilder builder = new BooleanBuilder();
        QDepartment qDepartment = QDepartment.department;

        if(StringUtils.hasText(condition.getName())) {
            builder.and(qDepartment.name.contains(condition.getName()));
        }

        if(StringUtils.hasText(condition.getDescription())) {
            builder.and(qDepartment.description.contains(condition.getDescription()));
        }

        if(pageStatus.getSort() == null) {
            pageStatus.addSort(new Sort(Sort.Direction.ASC, "name"));
        }

        builder.and(qDepartment.parent.eq(company.getDepartment()));

        return departmentRepository.findAll(builder, pageStatus);
    }

    @Override
    public Department save(Department department) {
        departmentRepository.saveAndFlush(department);
        return department;
    }
}
