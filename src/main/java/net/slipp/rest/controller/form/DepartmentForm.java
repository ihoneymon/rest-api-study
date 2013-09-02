package net.slipp.rest.controller.form;

import lombok.Data;
import lombok.ToString;
import net.slipp.rest.domain.Department;
import org.springframework.beans.BeanUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 8. 16
 */
@Data
@ToString
public class DepartmentForm {
    private String name;
    private String description;

    public Department bind(Department target) {
        BeanUtils.copyProperties(this, target);
        return target;
    }
}
