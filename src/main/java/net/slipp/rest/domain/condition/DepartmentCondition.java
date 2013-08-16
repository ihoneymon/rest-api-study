package net.slipp.rest.domain.condition;

import lombok.Data;

/**
 * 부서 검색조건
 *
 * @author: ihoneymon
 * Date: 13. 8. 16
 */
@Data
public class DepartmentCondition {
    private String name;
    private String description;
}
