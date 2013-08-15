package net.slipp.rest.support.exception;

import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 * Created with IntelliJ IDEA.
 * User: ihoneymon
 * Date: 13. 8. 15.
 * Time: 오후 6:10
 * To change this template use File | Settings | File Templates.
 */
public class SlippException extends RuntimeException {
    public SlippException(String msg) {
        super(msg);
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
