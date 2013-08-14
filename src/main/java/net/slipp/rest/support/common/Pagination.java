package net.slipp.rest.support.common;

import java.io.Serializable;
import java.lang.*;import java.lang.Double;import java.lang.IllegalArgumentException;import java.lang.Math;import java.lang.Override;import java.lang.String;import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.EqualsAndHashCode;

import org.springframework.data.domain.Sort;

/**
 * @param <T> the type of which the page consists.
 * @author 박용권
 */
@EqualsAndHashCode(of={"content", "pageNumber", "pageSize", "sort", "total"})
public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = -5288926843899242206L;
    
    private final List<T> content = new ArrayList<T>();
    private final int pageNumber;
    private final int pageSize;
    private final Sort sort;
    private final long total;
    
    private long blockSize = 10;
    
    private long currentPageNumber;
    private long beginPageNumber;
    private long endPageNumber;
    
    public Pagination(List<T> content, int pageNumber, int pageSize, Sort sort, long total) {

        if (null == content) {
            throw new IllegalArgumentException("Content must not be null!");
        }

        this.content.addAll(content);
        
        this.pageNumber =pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
        this.total = total;
        
        this.currentPageNumber = getNumber() + 1;
        this.endPageNumber = (long) Math.ceil(currentPageNumber / new Double(blockSize)) * blockSize;
        this.beginPageNumber = endPageNumber - blockSize + 1;
        
        this.endPageNumber = endPageNumber > getTotalPages() ? getTotalPages() : endPageNumber;
    }

    public int getNumber() {
        return pageNumber;
    }

    public int getSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return getSize() == 0 ? 0 : (int) Math.ceil((double) total / (double) getSize());
    }

    public int getNumberOfElements() {
        return content.size();
    }

    public long getTotalElements() {
        return total;
    }
    
    public int getNumberOfLastElement() {
        return (int) (total - (pageNumber * pageSize));
    }

    public boolean hasPreviousPage() {
        return getNumber() > 0;
    }

    public boolean isFirstPage() {
        return !hasPreviousPage();
    }

    public boolean hasNextPage() {
        return ((getNumber() + 1) * getSize()) < total;
    }

    public boolean isLastPage() {
        return !hasNextPage();
    }
    
    public long getCurrentPageNumber() {
        return currentPageNumber;
    }
    
    public long getBeginPageNumber() {
        return beginPageNumber;
    }

    public long getEndPageNumber() {
        return endPageNumber;
    }
    
    public boolean isFirstBlock() {
        return beginPageNumber == 1;
    }

    public boolean isPrevBlock() {
        return beginPageNumber > blockSize;
    }

    public boolean isNextBlock() {
        return getTotalPages() > endPageNumber;
    }

    public boolean isLastBlock() {
        return getTotalPages() == endPageNumber;
    }

    public Iterator<T> iterator() {
        return content.iterator();
    }

    public List<T> getContent() {
        return content;
    }
    
    public int getContentSize() {
        return content != null ? content.size() : 0;
    }

    public boolean hasContent() {
        return content == null ? false : !content.isEmpty();
    }

    public Sort getSort() {
        return sort;
    }

    @Override
    public java.lang.String toString() {

        String contentType = "UNKNOWN";

        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
    }

}
