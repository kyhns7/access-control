package com.kyhns7.rbac.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;


/**
 * 查询参数
 *
 * @author kyhns7@outlook.com
 */
public class Query<T> {

    public IPage<T> getPage(Map<String, Object> params) {
        long curPage = 1;
        long limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Long.parseLong((String) params.get(Constant.PAGE));
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Long.parseLong((String) params.get(Constant.LIMIT));
        }
        return new Page<>(curPage, limit);
    }

    public IPage<T> getPage(PageParams params) {
        long curPage = 1;
        long pageSize = 10;

        if (params != null){
            if (params.getCurrPage() != null && params.getCurrPage() > 0) {
                curPage = params.getCurrPage();
            }
            if (params.getPageSize() != null && params.getPageSize() > 0) {
                pageSize = params.getPageSize();
            }
        }
        return new Page<>(curPage, pageSize);
    }

    public static class Constant {
        /**
         * 当前页码
         */
        public static final String PAGE = "page";
        /**
         * 每页显示记录数
         */
        public static final String LIMIT = "limit";
    }
}

