package net.slipp.rest.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * 사용자 도메인
 *
 * @author: ihoneymon
 * Date: 13. 7. 22
 */
@Entity
@ToString
@EqualsAndHashCode
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getUsername() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

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
