package com.rzk.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rzk.common.utils.PageUtils;
import com.rzk.common.utils.R;
import com.rzk.modules.sys.entity.MessageEntity;
import com.rzk.modules.sys.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-01-18
 */
@RestController
@RequestMapping("wx/message")
@Api(tags="")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Resource
    private Environment environment;

    @GetMapping("list")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("wx:message:list")
    public R page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtils page = messageService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("信息")
    @RequiresPermissions("wx:message:info")
    public R get(@PathVariable("id") Long id){
        QueryWrapper<MessageEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("message_id",id);
        MessageEntity messageEntity = messageService.getOne(queryWrapper);
        String messageImage = messageEntity.getMessageImage();
        if (messageImage!=null){
            //添加图片前缀
            messageEntity.setMessageImage(environment.getProperty("rzk.url")+messageImage);
        }
        return R.ok().put("messageById", messageEntity);
    }

    @PostMapping("/save")
    @ApiOperation("保存")
    @RequiresPermissions("wx:message:save")
    public R save(@RequestBody MessageEntity dto){
        //效验数据
        messageService.save(dto);

        return R.ok();
    }

    @PostMapping("/update")
    @ApiOperation("修改")
    @RequiresPermissions("wx:message:update")
    public R update(@RequestBody MessageEntity dto){
        //效验数据
        messageService.updateById(dto);

        return R.ok();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @RequiresPermissions("wx:message:delete")
    public R delete(@RequestBody Long[] ids){
        //效验数据
        for (Long id : ids) {
            MessageEntity byId = messageService.getById(id);
            if (byId==null){
                return null;
            }else {
                messageService.removeById(byId.getMessageId());
            }
        }

        return R.ok();
    }


}