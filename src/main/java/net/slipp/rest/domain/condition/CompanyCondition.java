package net.slipp.rest.domain.condition;

import lombok.Data;

/**
 * 기업검색조건
 * User: ihoneymon
 * Date: 13. 8. 15
 * Time: 오전 7:27
 */
@Data
public class CompanyCondition {
    private String name;
    private String tel;
    private String address;
}
