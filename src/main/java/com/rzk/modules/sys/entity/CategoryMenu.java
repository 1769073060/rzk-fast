package com.rzk.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2022-12-11
 */
@Data

@EqualsAndHashCode(callSuper=false)
@TableName("category_menu")
public class CategoryMenu {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id",type= IdType.AUTO)//需要在实体类的主键属性上添加此句
	private int id;
    /**
     * 菜单名
     */
	private String label;
    /**
     * 菜单值
     */
	private String value;
    /**
     * 父菜单值
     */
	private String parentValue;
    /**
     * 状态
     */
	private String enable;
    /**
     * 
     */
	private String categoryImage;

	@TableField(exist = false)
	private String parentName;
}