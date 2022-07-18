package com.rzk.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rzk.common.utils.PageUtils;
import com.rzk.modules.sys.entity.WxResourceEntity;

import java.util.Map;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-07-17
 */
public interface WxResourceService extends IService<WxResourceEntity> {

    PageUtils queryPage(Map<String, Object> params);

}