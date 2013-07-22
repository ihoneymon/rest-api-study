package net.slipp.rest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 회사 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString(exclude = {"departments", "employees"})
public class Company implements Serializable {
    private static final long serialVersionUID = 2555196948716599267L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String name;
    @Getter @Setter(AccessLevel.PRIVATE)
    private String tel;
    @Getter @Setter(AccessLevel.PRIVATE)
    private String address;
    @OneToMany
    private Set<Department> departments;
    @OneToMany
    private Set<Employee> employees;

}
