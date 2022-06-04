package com.rzk.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;

import com.rzk.modules.generator.dao.UsersLikeVideosDao;
import com.rzk.modules.generator.entity.UsersLikeVideosEntity;
import com.rzk.modules.generator.service.UsersLikeVideosService;


@Service("usersLikeVideosService")
public class UsersLikeVideosServiceImpl extends ServiceImpl<UsersLikeVideosDao, UsersLikeVideosEntity> implements UsersLikeVideosService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersLikeVideosEntity> page = this.page(
                new Query<UsersLikeVideosEntity>().getPage(params),
                new QueryWrapper<UsersLikeVideosEntity>().like("id",(String)params.get("key"))
                        .or().like("user_id", (String)params.get("key")).or().like("video_id", (String)params.get("key")));

        return new PageUtils(page);
    }

}