package net.slipp.rest.support.method;

import dreaminfra.ipams.common.excel.ExcelImport;
import dreaminfra.ipams.common.excel.poi.POIExcelImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class POIExcelImportMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {
    
    private static Logger logger = LoggerFactory.getLogger(POIExcelImportMethodArgumentResolver.class);

    @Inject
    public POIExcelImportMethodArgumentResolver(ConfigurableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ExcelImport.class.isAssignableFrom(parameter.getParameterType());
    }
    
    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        RequestParam annotation = parameter.getParameterAnnotation(RequestParam.class);
        return (annotation != null) ? new RequestParamNamedValueInfo(annotation) :  new RequestParamNamedValueInfo();
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest webRequest) throws Exception {

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        MultipartHttpServletRequest multipartRequest =
                WebUtils.getNativeRequest(servletRequest, MultipartHttpServletRequest.class);
        
        assertIsMultipartRequest(multipartRequest);
        
        MultipartFile multipartFile = multipartRequest.getFile(name);
        ExcelImport excelImport = new POIExcelImport(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
        
        logger.debug("create ExcelImport object from multipartFile({})", multipartFile.getOriginalFilename());
        
        return excelImport;
    }

    @Override
    protected void handleMissingValue(String paramName, MethodParameter parameter) throws ServletException {
        throw new MissingServletRequestParameterException(paramName, parameter.getParameterType().getSimpleName());
    }

    private void assertIsMultipartRequest(HttpServletRequest request) {
        if (!isMultipartRequest(request)) {
            throw new MultipartException("The current request is not a multipart request.");
        }
    }

    private boolean isMultipartRequest(HttpServletRequest request) {
        if (!"post".equals(request.getMethod().toLowerCase())) {
            return false;
        }
        String contentType = request.getContentType();
        return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
    }
    
    private class RequestParamNamedValueInfo extends NamedValueInfo {
        private RequestParamNamedValueInfo() {
                super("", false, ValueConstants.DEFAULT_NONE);
        }
        private RequestParamNamedValueInfo(RequestParam annotation) {
                super(annotation.value(), annotation.required(), annotation.defaultValue());
        }
    }

}
