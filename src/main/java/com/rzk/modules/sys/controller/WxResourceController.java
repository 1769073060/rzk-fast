package com.rzk.modules.demo.controller;

import com.rzk.common.annotation.LogOperation;
import com.rzk.common.constant.Constant;
import com.rzk.common.page.PageData;
import com.rzk.common.utils.ExcelUtils;
import com.rzk.common.utils.Result;
import com.rzk.common.validator.AssertUtils;
import com.rzk.common.validator.ValidatorUtils;
import com.rzk.common.validator.group.AddGroup;
import com.rzk.common.validator.group.DefaultGroup;
import com.rzk.common.validator.group.UpdateGroup;
import com.rzk.modules.demo.dto.WxResourceDTO;
import com.rzk.modules.demo.excel.WxResourceExcel;
import com.rzk.modules.demo.service.WxResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-07-17
 */
@RestController
@RequestMapping("demo/wxresource")
@Api(tags="")
public class WxResourceController {
    @Autowired
    private WxResourceService wxResourceService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("demo:wxresource:page")
    public Result<PageData<WxResourceDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<WxResourceDTO> page = wxResourceService.page(params);

        return new Result<PageData<WxResourceDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("demo:wxresource:info")
    public Result<WxResourceDTO> get(@PathVariable("id") Long id){
        WxResourceDTO data = wxResourceService.get(id);

        return new Result<WxResourceDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("demo:wxresource:save")
    public Result save(@RequestBody WxResourceDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        wxResourceService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("demo:wxresource:update")
    public Result update(@RequestBody WxResourceDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        wxResourceService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("demo:wxresource:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        wxResourceService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("demo:wxresource:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WxResourceDTO> list = wxResourceService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, WxResourceExcel.class);
    }

}