package com.rzk.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rzk.common.exception.RRException;
import com.rzk.common.utils.Constant;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.R;
import com.rzk.common.validator.ValidatorUtils;
import com.rzk.common.validator.group.AddGroup;
 import com.rzk.common.validator.group.UpdateGroup;

import com.rzk.modules.sys.entity.SysMenuEntity;
import com.rzk.modules.sys.entity.WxResourceEntity;
import com.rzk.modules.sys.service.WxResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-07-17
 */
@RestController
@RequestMapping("/wx/resource")
@Api(tags="")
public class WxResourceController {
    @Autowired
    private WxResourceService wxResourceService;

    @GetMapping("/list")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("wx:resource:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxResourceService.queryPage(params);

        return R.ok().put("page", page);
    }

    @ResponseBody
    @GetMapping("/info/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("wx:resource:info")
    public R get(@PathVariable("id") Long id){
        WxResourceEntity wxResourceById = wxResourceService.getById(id);

        return R.ok().put("wxResourceById", wxResourceById);
    }


    @PostMapping(value = "/save")
    @ApiOperation("保存")
    @RequiresPermissions("wx:resource:save")
    public R save(@RequestBody WxResourceEntity wxResource){
        //效验数据
        verifyForm(wxResource);
        wxResourceService.save(wxResource);

        return R.ok();
    }

    @PostMapping(value = "/update")
    @ApiOperation("修改")
    @RequiresPermissions("wx:resource:update")
    public R update(@RequestBody WxResourceEntity wxResource){
        //效验数据
        verifyForm(wxResource);
        wxResourceService.updateById(wxResource);

        return R.ok();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @RequiresPermissions("wx:resource:delete")
    public R delete(@RequestBody Long[] ids){
        //效验数据
         for (Long id : ids) {
            WxResourceEntity byId = wxResourceService.getById(id);


            if (byId==null){
                return null;
            }else {
                wxResourceService.removeById(byId);
            }
        }



        return R.ok();
    }


    /**
     * 验证参数是否正确
     */
    private void verifyForm(WxResourceEntity wxResource){
        if(StringUtils.isBlank(wxResource.getFileName())){
            throw new RRException("文件不能为空");
        }

        if(wxResource.getDirectoryName() == null){
            throw new RRException("分类不能为空");
        }

    }

}