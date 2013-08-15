package net.slipp.rest.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.util.Assert;

import com.google.common.collect.Sets;

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

    @Getter
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Department> subDepartment = Sets.newHashSet();

    @Getter
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
}
