package net.slipp.rest.domain.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 사용자 권한
 *
 * @author: ihoneymon
 * Date: 13. 7. 26
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"role"})
@ToString
public class RoleGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -889770820117135351L;
    @Setter(AccessLevel.PRIVATE)
    @Id
    @Column(unique = true, updatable = false, length = 50)
    private String role;
    @Column(length = 100)
    private String name;
    @Column(length = 2000)
    private String description;
    @Column(name = "orderNumber")
    private Integer order;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = Calendar.getInstance().getTime();

    public RoleGrantedAuthority() {
    }

    public RoleGrantedAuthority(String role, String name, int order) {
        Assert.hasText(role, "Required Role value.");
        this.role = role;
        this.name = name;
        this.order = order;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
