package com.rzk.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;

import com.rzk.modules.generator.dao.UsersDao;
import com.rzk.modules.generator.entity.UsersEntity;
import com.rzk.modules.generator.service.UsersService;


@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity> implements UsersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersEntity> page = this.page(
                new Query<UsersEntity>().getPage(params),
                new QueryWrapper<UsersEntity>().like("id",(String)params.get("key"))
                        .or().like("username", (String)params.get("key")).or().like("nickname", (String)params.get("key")));

        return new PageUtils(page);
    }

}