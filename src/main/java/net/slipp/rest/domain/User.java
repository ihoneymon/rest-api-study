package net.slipp.rest.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 사용자 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = -1830782295321224536L;

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String username;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String password;

    /**
     * 사용자와 직원 정보는 1:1 매핑!
     */
    @OneToOne
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Employee employee;

    /**
     * 사용자의 권한
     */
    @ManyToMany
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Set<RoleAuthority> authorities;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Date createDate = Calendar.getInstance().getTime();
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Date modifiedDate = Calendar.getInstance().getTime();

    @Override
    public boolean isAccountNonExpired() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnabled() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
