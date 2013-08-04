package net.slipp.rest.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * 회사 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Data
@Entity
@ToString(exclude = {"department"})
public class Company implements Serializable {
    private static final long serialVersionUID = 2555196948716599267L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String tel;
    private String address;
    
    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @OrderBy("name asc")
    private Department department = new Department(null, name + "_root", "Root Department");
    
    Company() {}
    
    public Company(String name) {
        this.name = name;
    }
}
