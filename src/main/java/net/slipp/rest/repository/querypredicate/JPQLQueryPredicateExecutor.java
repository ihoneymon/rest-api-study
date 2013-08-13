package net.slipp.rest.repository.querypredicate;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.Predicate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ihoneymon
 * Date: 13. 8. 13
 */
public interface JPQLQueryPredicateExecutor<T> {
    List<T> findAll(JPQLQuery query);

    Page<T> findAll(JPQLQuery query, Pageable pageable);

    JPQLQuery createQuery(Predicate... predicate);
}
