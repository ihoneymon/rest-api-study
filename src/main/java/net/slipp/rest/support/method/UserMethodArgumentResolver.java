package net.slipp.rest.support.method;

import javax.inject.Inject;

import net.slipp.rest.domain.User;
import net.slipp.rest.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author 박용권
 * @Controller의 핸들러에서 현재 인증된 사용자(<code>User</code>)를 얻을때 사용할 HandlerMethodArgumentResolver이다.
 * <p/>
 * 인증된 사용자 정보가 있으면 사용자가 가지고 있는 직원(<code>Employee</code>)을 EntityManager로 영속성 컨텍스트에
 * 재진입 시킨 후 반환하고, 없으면 <code>AnonymousUser</code>를 생성해서 반환한다.
 */
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final static Logger logger = LoggerFactory.getLogger(UserMethodArgumentResolver.class);

    @Inject
    private UserRepository userRepository;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return User.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && User.class.isAssignableFrom(authentication.getPrincipal().getClass())) {

            User user = (User) authentication.getPrincipal();
            user = userRepository.findUserByUsername(user.getUsername());

            logger.debug("found securityContextHolder-bound authentication: {}", user);

            return user;
        }

        logger.debug("no authentication. returns null object.");

        return null;
    }

}
