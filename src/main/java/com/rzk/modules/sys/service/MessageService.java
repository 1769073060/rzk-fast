package com.rzk.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rzk.common.utils.PageUtils;
import com.rzk.modules.sys.entity.MessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-01-18
 */
public interface MessageService extends IService<MessageEntity>  {

    PageUtils queryPage(Map<String, Object> params);

}