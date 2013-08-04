package net.slipp.rest.controller.form;

import org.springframework.beans.BeanUtils;

import net.slipp.rest.domain.Company;
import lombok.Data;

@Data
public class CompanyForm {

    private Long id = -1L;
    private String name;
    private String tel;
    private String address;
    
    CompanyForm() {}
    
    public Company createCompany() {
        Company target = new Company(getName());
        BeanUtils.copyProperties(this, target, new String[] {"id"});
        return target;
    }
}
