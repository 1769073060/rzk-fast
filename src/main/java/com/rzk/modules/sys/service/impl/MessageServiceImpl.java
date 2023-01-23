package com.rzk.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;
import com.rzk.modules.sys.dao.MessageDao;
import com.rzk.modules.sys.entity.MessageEntity;
import com.rzk.modules.sys.entity.WxResourceEntity;
import com.rzk.modules.sys.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-01-18
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String filename = (String)params.get("fileName");

        IPage<MessageEntity> page = this.page(
                new Query<MessageEntity>().getPage(params),
                new QueryWrapper<MessageEntity>()
                        .like(org.apache.commons.lang.StringUtils.isNotBlank(filename),"file_name", filename).orderByDesc("message_id")
        );

        return new PageUtils(page);
    }


    public QueryWrapper<MessageEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<MessageEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}