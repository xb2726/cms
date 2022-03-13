package com.serendipity.base.entity.dto;

import com.serendipity.extra.query.Condition;
import com.serendipity.extra.query.MatchMode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 部门
 * @Author: bin
 * @Date:   2022-03-09
 */
@Getter
@Setter
public class SysDepQueryDTO {

	/**id*/
    @ApiModelProperty(value = "id")
    @Condition(target = "ID",machMode = MatchMode.EQ)
	private java.lang.String id;
	/**部门名称*/
    @ApiModelProperty(value = "部门名称")
    @Condition(target = "NAME",machMode = MatchMode.EQ)
	private java.lang.String name;
	/**上级部门id*/
    @ApiModelProperty(value = "上级部门id")
    @Condition(target = "P_ID",machMode = MatchMode.EQ)
	private java.lang.String pId;
	/**部门负责人*/
    @ApiModelProperty(value = "部门负责人")
    @Condition(target = "CHARGER",machMode = MatchMode.EQ)
	private java.lang.String charger;
	/**部门负责人id*/
    @ApiModelProperty(value = "部门负责人id")
    @Condition(target = "CHARGER_ID",machMode = MatchMode.EQ)
	private java.lang.String chargerId;
	/**负责人电话*/
    @ApiModelProperty(value = "负责人电话")
    @Condition(target = "CHARGER_MOBILE",machMode = MatchMode.EQ)
	private java.lang.String chargerMobile;
	/**部门状态  1 有效   0禁用*/
    @ApiModelProperty(value = "部门状态  1 有效   0禁用")
    @Condition(target = "STATUS",machMode = MatchMode.EQ)
	private java.lang.Integer status;
	/**排序*/
    @ApiModelProperty(value = "排序")
    @Condition(target = "SORT",machMode = MatchMode.EQ)
	private java.lang.Integer sort;
	/**creatorId*/
    @ApiModelProperty(value = "creatorId")
    @Condition(target = "CREATOR_ID",machMode = MatchMode.EQ)
	private java.lang.String creatorId;
	/**createDate*/
    @ApiModelProperty(value = "createDate")
    @Condition(target = "CREATE_DATE",machMode = MatchMode.EQ)
	private java.util.Date createDate;
	/**updaterId*/
    @ApiModelProperty(value = "updaterId")
    @Condition(target = "UPDATER_ID",machMode = MatchMode.EQ)
	private java.lang.String updaterId;
	/**updateDate*/
    @ApiModelProperty(value = "updateDate")
    @Condition(target = "UPDATE_DATE",machMode = MatchMode.EQ)
	private java.util.Date updateDate;
	/**reserved1*/
    @ApiModelProperty(value = "reserved1")
    @Condition(target = "RESERVED1",machMode = MatchMode.EQ)
	private java.lang.String reserved1;
	/**reserved2*/
    @ApiModelProperty(value = "reserved2")
    @Condition(target = "RESERVED2",machMode = MatchMode.EQ)
	private java.lang.String reserved2;
	/**deleted*/
    @ApiModelProperty(value = "deleted")
    @Condition(target = "DELETED",machMode = MatchMode.EQ)
	private java.lang.Integer deleted;
}
