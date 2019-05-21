package com.gexiao.lift.common;


import java.util.HashMap;
import java.util.Map;

/**
 * 通用的分页工具
 *
 * @author : lzx
 * created on 2019/1/22
 */
public class Pagination {

    /**
     * 默认每页记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认当前页码
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 当前页码
     */
    private int pageNum = DEFAULT_PAGE_NUM;
    /**
     * 每页数量
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 查询条件
     */
    private Map<String, Object> query = new HashMap<>();

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getQuery() {
        return query;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }
}
