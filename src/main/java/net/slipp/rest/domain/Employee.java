package net.slipp.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.util.Assert;

/**
 * 직원 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString(exclude = {"company", "departments"})
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable {
    private static final long serialVersionUID = 890213302538150678L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    @Setter
    private String nickName;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany
    @Getter
    @Setter
    private Set<Department> departments = Sets.newHashSet();

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Date createdDate;

    public Employee(Company company, String name, String email) {
        this.company = company;
        setName(name);
        setEmail(email);
    }

    public Employee() {
    }


    /**
     * 부서 추가
     * @param department
     * @return
     */
    public Employee addDepartment(Department department) {
        if(!getDepartments().contains(department)) {
            getDepartments().add(department);
        }

        return this;
    }

    /**
     * 부서 제거
     * @param department
     * @return
     */
    public Employee removeDepartment(Department department) {
        if(!getDepartments().contains(department)) {
            getDepartments().remove(department);
        }

        return this;
    }

    public void setName(String name) {
        Assert.hasText(name, "system.exception.requiredValue.name");
        this.name = name;
    }

    public void setEmail(String email) {
        Assert.hasText(email, "system.exception.requiredValue.email");
        this.email = email;
    }
}
