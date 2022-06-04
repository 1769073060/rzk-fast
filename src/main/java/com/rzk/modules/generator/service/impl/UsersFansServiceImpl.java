package com.rzk.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;

import com.rzk.modules.generator.dao.UsersFansDao;
import com.rzk.modules.generator.entity.UsersFansEntity;
import com.rzk.modules.generator.service.UsersFansService;


@Service("usersFansService")
public class UsersFansServiceImpl extends ServiceImpl<UsersFansDao, UsersFansEntity> implements UsersFansService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersFansEntity> page = this.page(
                new Query<UsersFansEntity>().getPage(params),
                new QueryWrapper<UsersFansEntity>().like("id",(String)params.get("key"))
                        .or().like("user_id", (String)params.get("key")).or().like("fan_id", (String)params.get("key")));


        return new PageUtils(page);
    }

}