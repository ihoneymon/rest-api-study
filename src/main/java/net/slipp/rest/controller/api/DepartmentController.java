package net.slipp.rest.controller.api;

import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.QDepartment;
import net.slipp.rest.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
public class DepartmentController {

    @Inject
    private DepartmentService departmentService;

    /**
     * 기업의 하위부서 가져오기
     * @param company {@link Company}
     * @param map
     */
    @RequestMapping(value="/companies/{company}/departments", method=RequestMethod.GET)
    public void getDepartments(@PathVariable Company company, ModelMap map) {
        map.put("departments", company.getDepartment().getAllSubDepartments());
    }
}
