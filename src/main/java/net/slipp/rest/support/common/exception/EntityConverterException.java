package net.slipp.rest.support.common.exception;

import org.springframework.dao.DataAccessException;

/**
 * Entity Converting Occur Exception
 *
 * @author ihoneymon
 */
public class EntityConverterException extends DataAccessException {
    private static final long serialVersionUID = -3157303276154122307L;

    private Class<?> entityType;

    public EntityConverterException(Class<?> entityType, String msg, Throwable cause) {
        super(msg, cause);
    }

    public Class<?> getEntityType() {
        return entityType;
    }

}
