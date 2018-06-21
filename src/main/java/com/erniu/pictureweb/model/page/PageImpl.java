package com.erniu.pictureweb.model.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ErNiu
 * Date : 2016-06-21 04:00
 * 分页
 */
public class PageImpl<T> implements Page, Serializable {

    private static final int DEFAULT_PAGE_ITEM_NUMBER = 10;
    private static final long serialVersionUID = 3204689546228654382L;
    private int startIndex;
    private int pageNumber;
    private int pageSize;
    private int totalNumber;
    private List<T> elements;

    public PageImpl() {
    }

    public PageImpl(List<T> elements, int totalNumber) {
        this(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, totalNumber, elements);
    }

    public PageImpl(int pageNumber, int pageSize, int totalNumber, List<T> elements) {
        if (pageNumber < 0){
            pageNumber = 0;
        }

        if (pageSize < 1){
            pageSize = DEFAULT_PAGE_SIZE;
        }

        this.startIndex = (pageNumber - 1) * pageSize;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalNumber = totalNumber;
        this.elements = elements;

        if (this.pageNumber == 0){
            this.pageNumber = 1;
        }
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalNumber() {
        return totalNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalPageCount() {
        int totalPage = this.totalNumber / pageSize;
        return this.totalNumber % pageSize == 0 ? totalPage
                : totalPage + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPreviousPageNumber() {
        int previous = startIndex - 1;
        return previous > 0 ? (previous) : 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNextPageNumber() {
        int next = startIndex + 1;
        int lastPageNumber = getLastPageNumber();
        return next <= lastPageNumber ? next : lastPageNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNextPage() {
        return getLastPageNumber() > getStartIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPreviousPage() {
        return getStartIndex() > 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastPageNumber() {
        return totalNumber % this.pageSize == 0 ? totalNumber / this.pageSize : totalNumber / this.pageSize + 1;
    }

    @Override
    public List getElements() {
        return this.elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThisPageFirstElementIndex() {
        return (getStartIndex() - 1) * getPageSize() + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThisPageLastElementIndex() {
        int fullPage = getThisPageFirstElementIndex() + getPageSize() - 1;
        return getTotalNumber() < fullPage ? getTotalNumber()
                : fullPage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStartPage() {
        return ((this.pageNumber - 1) / DEFAULT_PAGE_ITEM_NUMBER) * DEFAULT_PAGE_ITEM_NUMBER + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLastPage() {
        final int lastDisplayedPageNumber = getStartPage() + DEFAULT_PAGE_ITEM_NUMBER - 1;
        if (getLastPageNumber() < lastDisplayedPageNumber) {
            return getLastPageNumber();
        }
        return lastDisplayedPageNumber;
    }
}
