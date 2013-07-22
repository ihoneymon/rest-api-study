package net.slipp.rest.domain;

import com.google.common.collect.Sets;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    @Getter @Setter(AccessLevel.PRIVATE)
    @OneToMany
    private Set<Employee> employees = Sets.newHashSet();
}
