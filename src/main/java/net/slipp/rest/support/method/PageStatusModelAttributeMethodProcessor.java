package net.slipp.rest.support.method;

import dreaminfra.ipams.common.lang.annotation.PageStatusModel;
import dreaminfra.ipams.web.support.view.PageStatus;
import dreaminfra.ipams.web.support.view.PageStatusFactory;
import net.slipp.rest.support.view.PageStatusFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.inject.Inject;

public class PageStatusModelAttributeMethodProcessor implements HandlerMethodArgumentResolver {

    private final static Logger LOGGER = LoggerFactory.getLogger(PageStatusModelAttributeMethodProcessor.class);
    
    @Inject
    PageStatusFactory pageStatusFactory;
    
    
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageStatusModel.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        String name = ModelFactory.getNameForParameter(parameter);
        Object target = BeanUtils.instantiateClass(parameter.getParameterType());

        WebDataBinder binder = binderFactory.createBinder(webRequest, target, name);
        PageStatus pageStatus = pageStatusFactory.create(webRequest);
        if (binder.getTarget() != null && pageStatus != null) {
            binder.bind(new MutablePropertyValues(pageStatus.getAttributes()));
            LOGGER.debug("{} bind to {}", new Object[]{pageStatus.getAttributes(), name});
        }

        return binder.getTarget();
    }

}
