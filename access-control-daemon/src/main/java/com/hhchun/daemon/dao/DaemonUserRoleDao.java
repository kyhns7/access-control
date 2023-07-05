package com.hhchun.daemon.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhchun.daemon.entity.domain.DaemonUserRoleEntity;
import com.hhchun.daemon.entity.dto.DaemonUserRoleSearchDto;
import com.hhchun.daemon.entity.vo.DaemonUserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 后台用户关联角色(多对多)
 *
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface DaemonUserRoleDao extends BaseMapper<DaemonUserRoleEntity> {

    Page<DaemonUserRoleVo> getDaemonUserRoleList(@Param("page") IPage<DaemonUserRoleEntity> page,
                                                 @Param("search") DaemonUserRoleSearchDto searchDto);
}
