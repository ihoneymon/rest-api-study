package net.slipp.rest.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 직원 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString
@EqualsAndHashCode
public class Employee implements Serializable {
    private static final long serialVersionUID = 890213302538150678L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String name;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String email;

    @Getter @Setter(AccessLevel.PRIVATE)
    private String nickName;

    @ManyToMany
    @Getter @Setter(AccessLevel.PRIVATE)
    private Department department;

    @Getter @Setter(AccessLevel.PRIVATE)
    private Date createdDate;

}
