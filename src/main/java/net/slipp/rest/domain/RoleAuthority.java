package net.slipp.rest.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

/**
 * 사용자 권한 도메인
 */
@Entity
@ToString
@EqualsAndHashCode
public class RoleAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 4882929396458778017L;

    @Id
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String role;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String description;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Date createDate = Calendar.getInstance().getTime();

    @Override
    public String getAuthority() {
        return role;
    }
}
