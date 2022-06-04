package com.rzk.modules.generator.dao;

import com.rzk.modules.generator.entity.VideosEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 视频表
 * 
 * @author Liu RuiTao
 * @email 1044973286@qq.com
 * @date 2020-05-08 09:49:05
 */
@Mapper
public interface VideosDao extends BaseMapper<VideosEntity> {
	
}
