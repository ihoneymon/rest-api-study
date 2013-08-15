package net.slipp.rest.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.google.common.collect.ImmutableSet;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import net.slipp.rest.support.exception.SlippException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

/**
 * 부서 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString(exclude = {"parent"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode
public class Department implements Serializable {
    private static final long serialVersionUID = 1091151988674342774L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    private String name;
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String description;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    private Department parent;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Department> subDepartments = Sets.newHashSet();

    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate = Calendar.getInstance().getTime();

    public Department() {
    }

    public Department(Department parent, String name, String description) {
        this.parent = parent;
        this.name = name;
        this.description = description;
    }

    public Department setName(String name) {
        Assert.hasText(name, "${department.has.name}");
        this.name = name;
        return this;
    }

    public boolean hasSubDepartments() {
        return !CollectionUtils.isEmpty(subDepartments);
    }

    @JsonIgnore
    public ImmutableSet<Department> getAllSubDepartments() {
        ImmutableSet.Builder<Department> builder = ImmutableSet.builder();

        for (Department department : getSubDepartments()) {
            builder.add(department);

            if (department.hasSubDepartments()) {
                builder.addAll(department.getAllSubDepartments());
            }
        }

        ImmutableSet<Department> result = builder.build();

        return result;
    }

    public boolean isRootDepartment() {
        return this.getParent() == null;
    }

    public Department addSubDepartment(final String departmentName, String description) {
        Department subDepartment = new Department(this, departmentName, description);

        if (subDepartments.contains(subDepartment)) {
            throw new SlippException(departmentName + "은(는) 이미 하위부서로 등록된 부서입니다.");
        }

        subDepartments.add(subDepartment);
        return subDepartment;
    }

    public void removeSubDepartment(String departmentName, String description) {
        Department subDepartment = new Department(this, departmentName, description);
        subDepartments.remove(subDepartment);
    }

    @JsonIgnore
    public ImmutableSet<Department> getSubDepartments() {
        return ImmutableSet.copyOf(subDepartments);
    }
}
