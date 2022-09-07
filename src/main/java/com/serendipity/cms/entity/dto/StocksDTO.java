package com.serendipity.cms.entity.dto;

import com.serendipity.extra.query.Condition;
import com.serendipity.extra.query.MatchMode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
@Getter
@Setter
public class StocksDTO {

	/**id*/
    @ApiModelProperty(value = "id")
    @Condition(target = "ID",machMode = MatchMode.EQ)
	private java.lang.String id;
	/**stockName*/
    @ApiModelProperty(value = "stockName")
    @Condition(target = "STOCK_NAME",machMode = MatchMode.EQ)
	private java.lang.String stockName;
	/**stockCode*/
    @ApiModelProperty(value = "stockCode")
    @Condition(target = "STOCK_CODE",machMode = MatchMode.EQ)
	private java.lang.String stockCode;
	/**orgCode*/
    @ApiModelProperty(value = "orgCode")
    @Condition(target = "ORG_CODE",machMode = MatchMode.EQ)
	private java.lang.String orgCode;
	/**orgName*/
    @ApiModelProperty(value = "orgName")
    @Condition(target = "ORG_NAME",machMode = MatchMode.EQ)
	private java.lang.String orgName;
	/**orgSName*/
    @ApiModelProperty(value = "orgSName")
    @Condition(target = "ORG_S_NAME",machMode = MatchMode.EQ)
	private java.lang.String orgSName;
	/**publishDate*/
    @ApiModelProperty(value = "publishDate")
    @Condition(target = "PUBLISH_DATE",machMode = MatchMode.EQ)
	private java.lang.String publishDate;
	/**infoCode*/
    @ApiModelProperty(value = "infoCode")
    @Condition(target = "INFO_CODE",machMode = MatchMode.EQ)
	private java.lang.String infoCode;
	/**pdfUrl*/
    @ApiModelProperty(value = "pdfUrl")
    @Condition(target = "PDF_URL",machMode = MatchMode.EQ)
	private java.lang.String pdfUrl;
	/**title*/
    @ApiModelProperty(value = "title")
    @Condition(target = "TITLE",machMode = MatchMode.LIKE)
	private java.lang.String title;
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
