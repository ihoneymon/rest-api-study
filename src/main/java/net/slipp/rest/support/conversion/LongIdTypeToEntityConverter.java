package net.slipp.rest.support.conversion;

import com.google.common.collect.Sets;
import net.slipp.rest.domain.Company;
import net.slipp.rest.domain.Department;
import net.slipp.rest.domain.Employee;
import net.slipp.rest.domain.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.NumberUtils;

import java.util.Set;

/**
 * Entity(Id : Long type) Converter
 *
 * @author: ihoneymon
 * Date: 13. 7. 26
 */
public class LongIdTypeToEntityConverter extends AbstractEntityConverter<Long> {

    @Override
    Set<ConvertiblePair> initConvertibleTypes() {
        Set<Class<?>> targetTypes = Sets.newHashSet();
        targetTypes.add(Company.class);
        targetTypes.add(Department.class);
        targetTypes.add(Employee.class);
        targetTypes.add(User.class);

        Set<ConvertiblePair> convertiblePairs = Sets.newHashSet();
        for (Class<?> targetType : targetTypes) {
            convertiblePairs.add(new ConvertiblePair(Long.class, targetType));
            convertiblePairs.add(new ConvertiblePair(String.class, targetType));
        }
        return convertiblePairs;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Long convertId(Object source, TypeDescriptor sourceType) {
        if (Long.class.equals(sourceType.getType())) {
            return (Long) source;
        } else {
            return NumberUtils.parseNumber(source.toString(), Long.class);
        }
    }
}
