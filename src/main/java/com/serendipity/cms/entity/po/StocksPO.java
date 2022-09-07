package com.serendipity.cms.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serendipity.extra.query.Condition;
import com.serendipity.extra.query.MatchMode;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.serendipity.core.domain.BasePO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
@Getter
@Setter
@ApiModel(value="tb_stock对象", description="个股")
@TableName("tb_stock")
public class StocksPO extends BasePO {

	/**stockName*/
    @ApiModelProperty(value = "stockName")
    @TableField(value = "STOCK_NAME")
	private java.lang.String stockName;
	/**stockCode*/
    @ApiModelProperty(value = "stockCode")
    @TableField(value = "STOCK_CODE")
	private java.lang.String stockCode;
	/**orgCode*/
    @ApiModelProperty(value = "orgCode")
    @TableField(value = "ORG_CODE")
	private java.lang.String orgCode;
	/**orgName*/
    @ApiModelProperty(value = "orgName")
    @TableField(value = "ORG_NAME")
	private java.lang.String orgName;
	/**orgSName*/
    @ApiModelProperty(value = "orgSName")
    @TableField(value = "ORG_S_NAME")
	private java.lang.String orgSName;
	/**publishDate*/
    @ApiModelProperty(value = "publishDate")
    @TableField(value = "PUBLISH_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.sss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.sss")
	private Date publishDate;
	/**infoCode*/
    @ApiModelProperty(value = "infoCode")
    @TableField(value = "INFO_CODE")
	private java.lang.String infoCode;
	/**pdfUrl*/
    @ApiModelProperty(value = "pdfUrl")
    @TableField(value = "PDF_URL")
	private java.lang.String pdfUrl;
	/**title*/
    @ApiModelProperty(value = "title")
    @TableField(value = "TITLE")
	private java.lang.String title;

}
