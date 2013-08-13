package net.slipp.rest.support.conversion;

import java.util.Set;

import net.slipp.rest.domain.security.RoleGrantedAuthority;

import org.springframework.core.convert.TypeDescriptor;

import com.google.common.collect.Sets;

/**
 * Entity(Id : String type) Converter
 *
 * @author: ihoneymon
 * Date: 13. 7. 26
 */
public class StringIdTypeToEntityConverter extends AbstractEntityConverter<String> {
    @Override
    Set<ConvertiblePair> initConvertibleTypes() {
        Set<Class<?>> targetTypes = Sets.newHashSet();
        targetTypes.add(RoleGrantedAuthority.class);

        Set<ConvertiblePair> convertiblePairs = Sets.newHashSet();
        for (Class<?> targetType : targetTypes) {
            convertiblePairs.add(new ConvertiblePair(String.class, targetType));
            convertiblePairs.add(new ConvertiblePair(Long.class, targetType));
        }

        return convertiblePairs;
    }

    @Override
    String convertId(Object source, TypeDescriptor sourceType) {
        return source.toString();
    }
}
