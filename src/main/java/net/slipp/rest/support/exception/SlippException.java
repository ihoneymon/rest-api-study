package net.slipp.rest.support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 * REST API STUDY 프로젝트에서 사용하는 대표 Exception
 * User: ihoneymon
 * Date: 13. 8. 15.
 * Time: 오후 6:10
 */
public class SlippException extends RuntimeException {

    @Getter @Setter(AccessLevel.PRIVATE)
    private HttpStatus httpStatus;

    public SlippException(String msg) {
        super(msg);
    }

    public SlippException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public SlippException(Class<?> domainClass, String msg) {
        super(Introspector.decapitalize(ClassUtils.getShortName(domainClass)) + "." + msg);
    }

    public SlippException(String msg, Object... args) {
        super(String.format(msg, args));
    }

    public SlippException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
