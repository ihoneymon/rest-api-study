package net.slipp.rest.support.conversion;

import net.slipp.rest.support.common.exception.EntityConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

/**
 * Abstract Class for JPA Entity Converter
 * 등록된 Entity에 해당하는 객체가 들어왔을 경우, entityManager를 이용하여
 * 객체의 id로 검색하여 도출된 객체를 반환
 *
 * @param <ID> Entity IdClass
 * @author kim.jiheon
 */
abstract class AbstractEntityConverter<ID> implements GenericConverter {
    private static Logger logger = LoggerFactory.getLogger(AbstractEntityConverter.class);

    private final Set<ConvertiblePair> convertiblePairs;

    @PersistenceContext
    private EntityManager entityManager;


    public AbstractEntityConverter() {
        this.convertiblePairs = initConvertibleTypes();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        logger.debug("convert - source: {}, sourceType: {}, targetType: {}", new Object[]{source, sourceType, targetType});

        try {
            ID id = convertId(source, sourceType);
            Object entity = entityManager.getReference(targetType.getType(), id);

            logger.debug("found entity - type: {}, id: {}", new Object[]{targetType.getType(), id});
            logger.trace("found entity: {}", entity);

            return entity;
        } catch (Exception e) {
            if (targetType.getAnnotation(Nullable.class) != null) {
                return null;
            }

            throw new EntityConverterException(targetType.getType(), null, e);
        }
    }

    abstract Set<ConvertiblePair> initConvertibleTypes();

    abstract ID convertId(Object source, TypeDescriptor sourceType);

}
