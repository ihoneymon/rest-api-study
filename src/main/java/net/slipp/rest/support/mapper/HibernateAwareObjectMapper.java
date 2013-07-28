package net.slipp.rest.support.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.core.convert.ConversionService;

/**
 * Jackson JSON processor which handles Hibernate
 *
 * @author: ihoneymon
 * Date: 13. 7. 26
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -8599747631645197523L;
    
    ConversionService conversionService;

    public HibernateAwareObjectMapper(ConversionService conversionService) {
        this.conversionService = conversionService;

        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        Hibernate4Module hibernateModule = new Hibernate4Module();
        hibernateModule.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        registerModule(hibernateModule);
    }
}
