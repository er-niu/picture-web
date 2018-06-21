package com.erniu.pictureweb.model.page;

import java.util.Collections;
import java.util.List;

/**
 * Created by ErNiu
 * Date : 2016-06-21 04:00
 * 分页
 */
public interface Page<T> {

    int DEFAULT_PAGE_NUMBER = 1;
    int DEFAULT_PAGE_SIZE = 15;

    Page EMPTY_PAGE = new Page() {

        @Override
        public boolean hasNextPage() {
            return false;
        }

        @Override
        public boolean hasPreviousPage() {
            return false;
        }

        @Override
        public int getLastPageNumber() {
            return 0;
        }

        @Override
        public List getElements() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public int getTotalNumber() {
            return 0;
        }

        @Override
        public int getThisPageFirstElementIndex() {
            return 0;
        }

        @Override
        public int getThisPageLastElementIndex() {
            return 0;
        }

        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getNextPageNumber() {
            return 0;
        }

        @Override
        public int getPreviousPageNumber() {
            return 0;
        }

        @Override
        public int getStartIndex() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return 0;
        }

        @Override
        public int getTotalPageCount() {
            return 0;
        }

        @Override
        public int getStartPage() {
            return 0;
        }

        @Override
        public int getLastPage() {
            return 0;
        }
    };

    /**
     * 返回是否有下一页
     *
     * @return true表示有下一页；否则，没有下一页。
     */
    boolean hasNextPage();

    /**
     * 获取是否有上一页
     *
     * @return true，则表示有上一页；否则没有上一页
     */
    boolean hasPreviousPage();

    /**
     * 获取最后一页的页码。
     *
     * @return 最后一页的页码
     */
    int getLastPageNumber();

    /**
     * 获取当前页的所有元素列表。
     *
     * @return 当前页的所有元素列表
     */
    List<T> getElements();

    /**
     * 获取总的元素数目。
     *
     * @return 总的元素数目
     */
    int getTotalNumber();

    /**
     * 获取当前页第一个元素的number。
     *
     * @return 当前页第一个元素的number。
     */
    int getThisPageFirstElementIndex();

    /**
     * 获取当前页最后一个元素的number。
     *
     * @return 当前页最后一个元素的number。
     */
    int getThisPageLastElementIndex();

    int getPageNumber();

    /**
     * 获取下一页的页码。
     *
     * @return 下一页的页码
     */
    int getNextPageNumber();

    /**
     * 得到前一页的页码。
     *
     * @return 前一页的页码。
     */
    int getPreviousPageNumber();

    /**
     * 得到当前页码。
     *
     * @return 当前页码
     */
    int getStartIndex();

    /**
     * 获得每页显示的条目数目
     *
     * @return 每页显示的条目数目
     */
    int getPageSize();

    /**
     * 返回总页数。
     *
     * @return 总页数
     */
    int getTotalPageCount();

    /**
     * 返回循环显示页码的第一页页码。
     *
     * @return 循环显示页码的第一页页码。
     */
    int getStartPage();

    /**
     * 返回循环显示页码的最后一页页码。
     *
     * @return 循环显示页码的最后一页页码。
     */
    int getLastPage();
}
