package com.rzk.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rzk.common.utils.PageUtils;
import com.rzk.modules.generator.entity.VideosEntity;

import java.util.Map;

/**
 * 视频表
 *
 * @author Liu RuiTao
 * @email 1044973286@qq.com
 * @date 2020-05-08 09:49:05
 */
public interface VideosService extends IService<VideosEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

