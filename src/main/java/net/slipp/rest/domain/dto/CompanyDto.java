package net.slipp.rest.domain.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: ihoneymon
 * Date: 13. 8. 15.
 * Time: 오후 4:27
 * To change this template use File | Settings | File Templates.
 */
@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String tel;
    private String address;
    private Long departmentId;
}
