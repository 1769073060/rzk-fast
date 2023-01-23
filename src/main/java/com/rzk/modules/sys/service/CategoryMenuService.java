package com.rzk.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rzk.common.utils.PageUtils;
import com.rzk.modules.sys.entity.CategoryMenu;


import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-12-11
 */
public interface CategoryMenuService extends IService<CategoryMenu> {

    List<CategoryMenu> getCategoryMenuI();

    List<CategoryMenu> getCategoryMenuII(String value);

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryMenu> queryNotFiveList();

}