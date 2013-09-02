package net.slipp.rest.service;

import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.condition.DepartmentCondition;
import net.slipp.rest.support.view.PageStatus;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Transactional
public interface DepartmentService {
    /**
     * 기업 부서 목록
     *
     *
     *
     * @param company {@link net.slipp.rest.domain.Company}
     * @param condition
     *@param pageStatus  @return
     */
    @Transactional(readOnly = true)
    Page<Department> getDepartments(Company company, DepartmentCondition condition, PageStatus pageStatus);

    /**
     * 부서 저장
     * @param department {@link Department}
     * @return
     */
    Department save(Department department);

}
