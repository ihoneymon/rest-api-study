package net.slipp.rest.controller.api;

import net.slipp.rest.controller.form.DepartmentForm;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.QDepartment;
import net.slipp.rest.domain.condition.DepartmentCondition;
import net.slipp.rest.service.DepartmentService;
import net.slipp.rest.support.common.Pagination;
import net.slipp.rest.support.common.Paginations;
import net.slipp.rest.support.exception.SlippException;
import net.slipp.rest.support.view.PageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * 부서 컨트롤러
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Controller
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Inject
    private DepartmentService departmentService;

    @Inject
    private MessageSourceAccessor messageSourceAccessor;

    /**
     * 기업의 하위부서 가져오기
     * @param company {@link Company}
     * @param map
     */
    @RequestMapping(value="/companies/{company}/departments", method=RequestMethod.GET)
    public void getDepartments(@PathVariable Company company, DepartmentCondition condition, PageStatus pageStatus, ModelMap map) {
        Pagination<Department> page = Paginations.pagination(departmentService.getDepartments(company, condition, pageStatus));
        map.put("departments", page);
    }

    @RequestMapping(value="/companies/{company}/departments/{department}", method=RequestMethod.GET)
    public void getDepartment(@PathVariable Company company, @PathVariable Department department, ModelMap map) {
        map.put("department", department);
    }

    /**
     * 기업의 하위부서 추가
     * @param company
     * @param form
     * @param map
     */
    @RequestMapping(value="/companies/{company}/departments", method=RequestMethod.POST)
    public void saveDepartment(@PathVariable Company company, @RequestBody DepartmentForm form, ModelMap map) {
        try {
            Department rootDepartment = company.getDepartment();
            Department subDepartment = rootDepartment.addSubDepartment(form.getName(), form.getDescription());
            logger.debug("new SubDepartment : {}", subDepartment);
            departmentService.save(subDepartment);
            departmentService.save(rootDepartment);
            map.put("code", HttpStatus.OK);
        } catch(SlippException e) {
            map.put("code", e.getHttpStatus());
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 기업의 하위부서 수정
     */
    /**
     * 기업 하위부서 정보 수정
     * @param company {@link Company} 기업
     * @param department {@link Department} 수정대상
     * @param form {@link DepartmentForm} 수정내용
     * @param map
     */
    @RequestMapping(value="/companies/{company}/departments/{department}", method=RequestMethod.PUT)
    public void updateDepartment(@PathVariable Company company, @PathVariable Department department, @RequestBody DepartmentForm form, ModelMap map) {
        try {
            form.bind(department);
            departmentService.save(department);
            map.put("code", HttpStatus.OK);
        } catch(SlippException e) {
            map.put("code", e.getHttpStatus());
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }

    /**
     * 기업 하위부서 삭제
     * @param company {@link Company} 기업정보
     * @param department {@link Department} 삭제 부서
     * @param map
     */
    @RequestMapping(value="/companies/{company}/departments/{department}", method=RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable Company company, @PathVariable Department department, ModelMap map) {
        try {
            company.getDepartment().removeSubDepartment(department.getName(), department.getDescription());
            departmentService.save(company.getDepartment());
            map.put("code", HttpStatus.OK);
        } catch(SlippException e) {
            map.put("code", e.getHttpStatus());
            map.put("msg", messageSourceAccessor.getMessage(e.getMessage()));
        }
    }
}
