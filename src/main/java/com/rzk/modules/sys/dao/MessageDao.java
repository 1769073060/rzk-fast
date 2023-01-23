package com.rzk.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rzk.modules.sys.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-01-18
 */
@Mapper
public interface MessageDao extends BaseMapper<MessageEntity> {
	
}