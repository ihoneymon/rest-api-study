package net.slipp.rest.support.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.core.convert.ConversionService;

/**
 * Jackson JSON processor which handles Hibernate
 * <a href="http://blog.pastelstudios.com/2012/03/12/spring-3-1-hibernate-4-jackson-module-hibernate/">Spring 3.1, Hibernate 4 and Jackson-Module-Hibernate</a>
 *
 * @author: ihoneymon
 * Date: 13. 7. 26
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -8599747631645197523L;

    public HibernateAwareObjectMapper() {
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);

        Hibernate4Module hibernateModule = new Hibernate4Module();
        hibernateModule.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        registerModule(hibernateModule);
    }
}
