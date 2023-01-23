package com.rzk.modules.sys.dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.rzk.modules.sys.entity.CategoryMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-12-11
 */
@Mapper
public interface CategoryMenuDao extends BaseMapper<CategoryMenu> {

    @Select("select * from category_menu ${ew.customSqlSegment}")
    List<CategoryMenu> getCategoryMenuI(@Param(Constants.WRAPPER) Wrapper<CategoryMenu> wrapper);

    @Select("select * from category_menu ${ew.customSqlSegment}")
    List<CategoryMenu> getCategoryMenuII(@Param(Constants.WRAPPER) Wrapper<CategoryMenu> wrapper);

    @Select("select * from  `rzk-community`.category_menu where LENGTH(value)!=5")
    List<CategoryMenu> queryNotFiveList();
}