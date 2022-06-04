package com.rzk.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rzk.common.utils.PageUtils;
import com.rzk.modules.generator.entity.CommentsEntity;

import java.util.Map;

/**
 *  用户留言表
 *
 * @author Liu RuiTao
 * @email 1044973286@qq.com
 * @date 2020-05-08 09:49:05
 */
public interface CommentsService extends IService<CommentsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

