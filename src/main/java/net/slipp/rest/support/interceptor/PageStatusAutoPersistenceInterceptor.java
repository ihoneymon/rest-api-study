package net.slipp.rest.support.interceptor;

import net.slipp.rest.support.view.PageStatus;
import net.slipp.rest.support.view.PageStatusFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.inject.Inject;
import java.util.Collection;

public class PageStatusAutoPersistenceInterceptor implements WebRequestInterceptor {

    public static final String DEFAULT_ATTRIBUTE_NAME = "pageStatus";
    private static final Logger LOGGER = LoggerFactory.getLogger(PageStatusAutoPersistenceInterceptor.class);
    @Inject
    PageStatusFactory factory;

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        if (model != null) {
            PageStatus pageStatus = findPageStatus(model.values());
            if (pageStatus == null) {
                pageStatus = factory.create(request);

                model.addAttribute(DEFAULT_ATTRIBUTE_NAME, pageStatus);

                LOGGER.debug("model add {}", pageStatus);
            }
        }
    }

    private PageStatus findPageStatus(Collection<?> values) {
        for (Object value : values) {
            if (ClassUtils.isAssignableValue(PageStatus.class, value)) {
                return (PageStatus) value;
            }
        }
        return null;
    }

    @Override
    public void preHandle(WebRequest request) throws Exception {
        // nothing
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex)
            throws Exception {
        // nothing
    }

}
