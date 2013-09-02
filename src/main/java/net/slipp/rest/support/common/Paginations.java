package net.slipp.rest.support.common;

import net.slipp.rest.support.mapper.ExtensibleModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class Paginations {

    private static final ExtensibleModelMapper modelMapper = new ExtensibleModelMapper();

    private Paginations() {
    }

    public static Pagination<Object> empty(Pageable pageable) {
        return new Pagination<Object>(Collections.emptyList(), pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort(), 0);
    }

    public static <D> Pagination<D> pagination(List<D> contents, Pageable pageable) {
        return new Pagination<D>(contents, pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort(), contents.size());
    }

    public static <D> Pagination<D> pagination(Page<D> page) {
        return new Pagination<D>(page.getContent(), page.getNumber(), page.getSize(), page.getSort(), page.getTotalElements());
    }

    public static <D> Pagination<D> transform(Page<?> page, Class<D> destinationType) {
        List<D> content = null;
        if (CollectionUtils.isEmpty(page.getContent())) {
            content = Collections.emptyList();
        } else {
            content = modelMapper.map(page.getContent(), destinationType);
        }

        return new Pagination<D>(content, page.getNumber(), page.getSize(), page.getSort(), page.getTotalElements());
    }
}
