package com.serendipity.cms.entity.po;

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
 * @Description: 行业研报浏览记录
 * @Author: bin
 * @Date:   2022-03-06
 */
@Getter
@Setter
@ApiModel(value="tb_industry_record对象", description="行业研报浏览记录")
@TableName("tb_industry_record")
public class IndustryRecordPO extends BasePO {

	/**userId*/
    @ApiModelProperty(value = "userId")
    @TableField(value = "USER_ID")
	private java.lang.String userId;
	/**industryId*/
    @ApiModelProperty(value = "industryId")
    @TableField(value = "INDUSTRY_ID")
	private java.lang.String industryId;

}
