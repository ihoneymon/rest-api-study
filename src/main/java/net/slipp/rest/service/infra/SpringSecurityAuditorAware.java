package net.slipp.rest.service.infra;

import net.slipp.rest.domain.Employee;
import net.slipp.rest.domain.User;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 스프링 시큐리티의 로그인을 통과한 상태에서 인증정보에서 현재 사용자 정보를 가져온다.
 *
 * @author kim.jiheon
 */
public class SpringSecurityAuditorAware implements AuditorAware<Employee> {
    @Override
    public Employee getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).getEmployee();
        }

        return null;
    }
}
