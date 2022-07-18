package com.rzk.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
 import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 *
 * @author
 * @since 1.0.0 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("wx_resource")
public class WxResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建者
     */
	private String createMaster;
    /**
     * url地址
     */
	private String url;
    /**
     * uri地址
     */
	private String uri;
    /**
     * 提取码
     */
	private String fetchCode;
    /**
     * 文件名
     */
	private String fileName;
    /**
     * 目录名
     */
	private String directoryName;
    /**
     * 系统版本
     */
	private String systemVersion;
    /**
     * 是否失效:0否1是
     */
	private Integer failureType;
    /**
     * 生成二维码
     */
	private String qrCode;
    /**
     * pdf
     */
	private String pdf;
    /**
     * 使用次数
     */
	private String userNumber;
    /**
     * 逻辑删除:0未删除1已删除
     */
	private Integer deleted;
    /**
     * 修改时间
     */
	private Long updateTime;
    /**
     * 创建时间
     */
	private Long createTime;
    /**
     * 下载源:1.百度云,2蓝奏云
     */
	private String downloadSource;
    /**
     * 微信文章地址
     */
	private String articleAddresses;
}