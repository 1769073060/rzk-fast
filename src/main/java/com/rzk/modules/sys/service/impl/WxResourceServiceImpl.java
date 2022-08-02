package com.rzk.modules.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;
import com.rzk.modules.sys.dao.WxResourceDao;
import com.rzk.modules.sys.entity.SysConfigEntity;
import com.rzk.modules.sys.entity.WxResourceEntity;
import com.rzk.modules.sys.redis.SysConfigRedis;
import com.rzk.modules.sys.service.WxResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-07-17
 */
@Service
@DS("slave")
public class WxResourceServiceImpl extends ServiceImpl<WxResourceDao, WxResourceEntity> implements WxResourceService {


    @Autowired
    private WxResourceService wxResourceService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String filename = (String)params.get("key");

        IPage<WxResourceEntity> page = this.page(
                new Query<WxResourceEntity>().getPage(params),
                new QueryWrapper<WxResourceEntity>()
                        .like(StringUtils.isNotBlank(filename),"file_name", filename)
        );

        return new PageUtils(page);
    }

}