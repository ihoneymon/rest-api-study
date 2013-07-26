package net.slipp.rest.domain;

import com.google.common.collect.Sets;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 부서 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString(exclude = {"employees"})
@EqualsAndHashCode
public class Department implements Serializable {
    private static final long serialVersionUID = 1091151988674342774L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter @Setter(AccessLevel.PRIVATE)
    private String name;
    @Getter @Setter(AccessLevel.PRIVATE)
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    private Department parent;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Department> subDepartment = Sets.newHashSet();

    @Getter
    @Column(nullable = false, updatable = false)
    private Date createdDate = Calendar.getInstance().getTime();

    public Department() {
    }

    public Department(Department parent, String name, String description) {
        this.parent = parent;
    }

    public Department setName(String name) {
        Assert.hasText(name, "${department.has.name}");
        this.name = name;
        return this;
    }
}
