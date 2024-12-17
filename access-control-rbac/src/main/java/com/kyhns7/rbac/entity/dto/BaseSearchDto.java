package com.kyhns7.rbac.entity.dto;

import com.kyhns7.rbac.common.utils.PageParams;
import lombok.Data;

@Data
public class BaseSearchDto {
    /**
     * 当前页码
     */
    private Long currPage;
    /**
     * 每页纪录数
     */
    private Long pageSize;

    public PageParams getPageParams(){
        PageParams pageParams = new PageParams();
        pageParams.setCurrPage(currPage);
        pageParams.setPageSize(pageSize);
        return pageParams;
    }

}
