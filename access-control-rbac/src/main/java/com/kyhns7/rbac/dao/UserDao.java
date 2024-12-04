package com.kyhns7.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyhns7.rbac.entity.domain.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户
 * 
 * @author hhchun
 * @email 12487489@qq.com
 * @date 2023-07-03 06:53:50
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
