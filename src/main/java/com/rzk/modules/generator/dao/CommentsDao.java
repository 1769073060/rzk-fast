package com.rzk.modules.generator.dao;

import com.rzk.modules.generator.entity.CommentsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  用户留言表
 * 
 * @author Liu RuiTao
 * @email 1044973286@qq.com
 * @date 2020-05-08 09:49:05
 */
@Mapper
public interface CommentsDao extends BaseMapper<CommentsEntity> {
	
}
