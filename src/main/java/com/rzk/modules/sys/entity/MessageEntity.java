package com.rzk.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-01-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@TableName("message")
public class MessageEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 
     */
	@TableId(value = "message_id",type= IdType.AUTO)//需要在实体类的主键属性上添加此句
	private Integer messageId;
    /**
     * 
     */
	private Integer userIdAnonymity;
    /**
     * 
     */
	private Integer userId;
    /**
     * 
     */
	private String categoryId;
    /**
     * 
     */
	private String userPhone;
    /**
     * 
     */
	private String userMajor;
    /**
     * 
     */
	private String userLevel;
    /**
     * 
     */
	private String messageDetail;
    /**
     * 
     */
	private Integer messageShare;
    /**
     * 
     */
	private Integer messageComment;
    /**
     * 
     */
	private Integer messageWatch;
    /**
     * 
     */
	private Date messageCreatTime;
    /**
     * 图片
     */
	private String messageImage;
    /**
     * 标题
     */
	private String messageTitle;
    /**
     * 字菜单
     */
	private String categoryValueId;
}