package com.kyhns7.rbac.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PageParams {
    /**
     * 当前页码
     */
    private Long currPage;
    /**
     * 每页纪录数
     */
    private Long pageSize;

    // 默认页
    public static final long DEFAULT_CURR_PAGE = 1L;
    // 默认每页记录数
    public static final long DEFAULT_PAGE_SIZE = 10L;

    public <T> IPage<T> getPage(Class<T> T) {
        if (currPage <= 0) {
            currPage = DEFAULT_CURR_PAGE;
        }
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return new Page<>(currPage, pageSize);
    }

}
