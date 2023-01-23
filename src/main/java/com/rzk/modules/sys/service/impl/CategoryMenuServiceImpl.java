package com.rzk.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.Query;
import com.rzk.modules.sys.dao.CategoryMenuDao;
import com.rzk.modules.sys.entity.CategoryMenu;
import com.rzk.modules.sys.entity.WxResourceEntity;
import com.rzk.modules.sys.service.CategoryMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CategoryMenuServiceImpl extends ServiceImpl<CategoryMenuDao, CategoryMenu> implements CategoryMenuService {

    @Autowired
    private CategoryMenuDao categoryMenuDao;

    public QueryWrapper<CategoryMenu> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CategoryMenu> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CategoryMenu> getCategoryMenuI() {

        QueryWrapper<CategoryMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_value","0");
        return categoryMenuDao.getCategoryMenuI(queryWrapper);
    }

    @Override
    public List<CategoryMenu> getCategoryMenuII(String value) {

        QueryWrapper<CategoryMenu> queryWrapper = new QueryWrapper<>();
        System.out.println(value);
        queryWrapper.eq("parent_value",value);
        return categoryMenuDao.getCategoryMenuII(queryWrapper);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String label = (String)params.get("key");

        IPage<CategoryMenu> page = this.page(
                new Query<CategoryMenu>().getPage(params),
                new QueryWrapper<CategoryMenu>()
                        .like(org.apache.commons.lang.StringUtils.isNotBlank(label),"label", label)
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryMenu> queryNotFiveList() {

        return categoryMenuDao.queryNotFiveList();
    }
}