package net.slipp.rest.domain.condition;

import lombok.Data;
import lombok.ToString;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;

/**
 * 직원 검색조건
 * User: ihoneymon
 * Date: 13. 8. 16.
 * Time: 오후 7:35
 */
@Data
@ToString
public class EmployeeCondition {
    private String name;

    private String email;

    private String nickName;

    private Company company;

    private Department department;
}
