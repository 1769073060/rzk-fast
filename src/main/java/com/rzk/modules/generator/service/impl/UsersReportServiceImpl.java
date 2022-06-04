package com.rzk.modules.generator.service.impl;

import com.rzk.modules.generator.service.UsersReportService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;

import com.rzk.modules.generator.dao.UsersReportDao;
import com.rzk.modules.generator.entity.UsersReportEntity;


@Service("usersReportService")
public class UsersReportServiceImpl extends ServiceImpl<UsersReportDao, UsersReportEntity> implements UsersReportService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsersReportEntity> page = this.page(
                new Query<UsersReportEntity>().getPage(params),
                new QueryWrapper<UsersReportEntity>().like("id",(String)params.get("key"))
                        .or().like("deal_user_id", (String)params.get("key")).or().like("deal_video_id", (String)params.get("key"))
                        .or().like("content", (String)params.get("key"))  .or().like("userid", (String)params.get("key")));

        return new PageUtils(page);
    }

}