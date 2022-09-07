package com.serendipity.base.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.serendipity.core.domain.BasePO;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 部门
 * @Author: bin
 * @Date:   2022-03-09
 */
@Getter
@Setter
@ApiModel(value="sys_dep对象", description="部门")
public class SysDepPO extends BasePO {

	/**id*/
	@TableId(type = IdType.ID_WORKER)
    @ApiModelProperty(value = "id")
    @TableField(value = "ID")
	private java.lang.String id;
	/**部门名称*/
    @ApiModelProperty(value = "部门名称")
    @TableField(value = "NAME")
	private java.lang.String name;
	/**上级部门id*/
    @ApiModelProperty(value = "上级部门id")
    @TableField(value = "P_ID")
	private java.lang.String pId;
	/**部门负责人*/
    @ApiModelProperty(value = "部门负责人")
    @TableField(value = "CHARGER")
	private java.lang.String charger;
	/**部门负责人id*/
    @ApiModelProperty(value = "部门负责人id")
    @TableField(value = "CHARGER_ID")
	private java.lang.String chargerId;
	/**负责人电话*/
    @ApiModelProperty(value = "负责人电话")
    @TableField(value = "CHARGER_MOBILE")
	private java.lang.String chargerMobile;
	/**部门状态  1 有效   0禁用*/
    @ApiModelProperty(value = "部门状态  1 有效   0禁用")
    @TableField(value = "STATUS")
	private java.lang.Integer status;
	/**排序*/
    @ApiModelProperty(value = "排序")
    @TableField(value = "SORT")
	private java.lang.Integer sort;

}
