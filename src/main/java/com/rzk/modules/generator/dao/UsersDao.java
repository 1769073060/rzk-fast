package com.rzk.modules.generator.dao;

import com.rzk.modules.generator.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表

 * 
 * @author Liu RuiTao
 * @email 1044973286@qq.com
 * @date 2020-05-08 09:49:05
 */
@Mapper
public interface UsersDao extends BaseMapper<UsersEntity> {
	
}
