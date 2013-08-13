package net.slipp.rest.support.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
public class PageStatus implements Pageable, Serializable {

    private static final long serialVersionUID = 1584712873980436690L;

    private final int pageNumber;
    private final int pageSize;
    private final Sort sort;
    private final Map<String, Object> attributes;
    private final String queryString;
    private final String pageableQueryString;
    private final String jsonString;
    private final String pageableJsonString;

    public PageStatus(
            int pageNumber, int pageSize, Sort sort,
            Map<String, Object> attributes,
            String queryString, String pageableQueryString,
            String jsonString, String pageableJsonString) {

        if (0 > pageNumber) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if (0 >= pageSize) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
        this.attributes = attributes == null ? new HashMap<String, Object>() : attributes;
        this.queryString = queryString;
        this.pageableQueryString = pageableQueryString;
        this.jsonString = jsonString;
        this.pageableJsonString = pageableJsonString;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return pageNumber * pageSize;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public PageStatus addSort(Sort sort) {
        return new PageStatus(pageNumber, pageSize, sort, attributes,
                queryString, pageableQueryString, jsonString, pageableJsonString);
    }

    @Override
    public String toString() {
        return "PageStatus [pageNumber=" + pageNumber + ", pageSize="
                + pageSize + ", sort=" + sort + ", attributes=" + attributes + "]";
    }

}
