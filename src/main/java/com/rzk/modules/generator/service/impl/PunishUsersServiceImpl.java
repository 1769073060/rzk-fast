package com.rzk.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;

import com.rzk.modules.generator.dao.PunishUsersDao;
import com.rzk.modules.generator.entity.PunishUsersEntity;
import com.rzk.modules.generator.service.PunishUsersService;


@Service("punishUsersService")
public class PunishUsersServiceImpl extends ServiceImpl<PunishUsersDao, PunishUsersEntity> implements PunishUsersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PunishUsersEntity> page = this.page(
                new Query<PunishUsersEntity>().getPage(params),
                new QueryWrapper<PunishUsersEntity>().like("id",(String)params.get("key"))
                        .or().like("user_id", (String)params.get("key")));

        return new PageUtils(page);
    }

}