package net.slipp.rest.support.conversion;

import com.google.common.collect.ImmutableSet;
import net.slipp.rest.support.common.CodeEncodableEnum;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Set;

/**
 * {@link net.slipp.rest.support.common.CodeEncodableEnum} 인터페이스를 상속한 enum 타입의 code를 이용한 Converter
 *
 * @author: ihoneymon
 * Date: 13. 7. 26
 */
public class CodeEncodableEnumConverter implements GenericConverter {

    private Set<ConvertiblePair> convertiblePairs = null;

    public CodeEncodableEnumConverter(Set<ConvertiblePair> convertiblePairs) {
        this.convertiblePairs = ImmutableSet.<ConvertiblePair>builder().add(new ConvertiblePair(String.class, Object.class)).build();
        for (ConvertiblePair convertiblePair : convertiblePairs) {
            if (!CodeEncodableEnum.class.isAssignableFrom(convertiblePair.getTargetType())) {
                throw new IllegalArgumentException(convertiblePair.getTargetType().getName() + "did not implement the CodeEncodableEnum interface.");
            }
        }
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        String code = (String) source;

        @SuppressWarnings("unchecked")
        Class<? extends CodeEncodableEnum> targetClass = (Class<? extends CodeEncodableEnum>) targetType.getType();
        for (CodeEncodableEnum codeEncodableEnum : targetClass.getEnumConstants())
            if (codeEncodableEnum.getCode().equals(code)) return codeEncodableEnum;

        throw new IllegalArgumentException("Unknown code '" + code + "' for enum type " + targetType.getType().getName());
    }
}
