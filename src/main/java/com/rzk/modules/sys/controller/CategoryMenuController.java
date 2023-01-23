package com.rzk.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.R;
import com.rzk.modules.sys.entity.CategoryMenu;
import com.rzk.modules.sys.entity.SysMenuEntity;
import com.rzk.modules.sys.service.CategoryMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/wx/categorymenu")
@Api(tags="")
public class CategoryMenuController {
    @Autowired
    private CategoryMenuService categoryMenuService;

    @GetMapping("/list")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam( value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("wx:categorymenu:list")
    public List<CategoryMenu> page(@ApiIgnore @RequestParam Map<String, Object> params){
        List<CategoryMenu> menuList = categoryMenuService.list();
        for(CategoryMenu categoryMenu : menuList){
            QueryWrapper<CategoryMenu> queryWrapper = new QueryWrapper<>();
            CategoryMenu parentMenuEntity = categoryMenuService.getOne(queryWrapper.eq("value",categoryMenu.getParentValue()));
            if(parentMenuEntity != null){
                categoryMenu.setParentName(parentMenuEntity.getLabel());
            }
        }

        return menuList;
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("wx:categorymenu:info")
    public R get(@PathVariable("id") Long id){
        QueryWrapper<CategoryMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        CategoryMenu menu = categoryMenuService.getOne(queryWrapper);

        return R.ok().put("menu", menu);
    }

    @PostMapping("/save")
    @ApiOperation("保存")
    @RequiresPermissions("wx:categorymenu:save")
    public R save(@RequestBody CategoryMenu categoryMenu){
        //效验数据
        categoryMenuService.save(categoryMenu);

        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation("修改")
    @RequiresPermissions("wx:categorymenu:update")
    public R update(@RequestBody CategoryMenu categoryMenu){

        categoryMenuService.updateById(categoryMenu);

        return R.ok();
    }

    @PostMapping("/delete/{menuId}")
    @ApiOperation("删除")
    @RequiresPermissions("wx:categorymenu:delete")
    public R delete(@RequestBody Long[] ids){
        for (Long id : ids) {
            CategoryMenu byId = categoryMenuService.getById(id);
            if (byId==null){
                return null;
            }else {
                categoryMenuService.removeById(byId.getId());
            }
        }

        return R.ok();
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("wx:categorymenu:select")
    public R select(){
        //查询列表数据
        List<CategoryMenu> menuList = categoryMenuService.queryNotFiveList();

        return R.ok().put("menuList", menuList);
    }


}