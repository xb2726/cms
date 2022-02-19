package com.serendipity.cms.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.serendipity.core.domain.BasePO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther bin
 */
@Getter
@Setter
@TableName("tb_industry")
public class Industry extends BasePO {

    @TableField(value ="title")
    private String title;

    @TableField( value="org_code" )
    private String orgCode;

    @TableField( value="org_name" )
    private String orgName;

    @TableField( value="org_s_name" )
    private String orgSName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.sss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.sss")
    @TableField( value="publish_date"  )
    private Date publishDate;

    @TableField( value="info_code"  )
    private String infoCode;

    /* @ApiField(value="colzumn")
     @TableField( name="column"  ,length=100)
     private String column;
 */
    @TableField( value="industry_code"  )
    private String industryCode;

    @TableField( value="industry_name" )
    private String industryName;

    @TableField( value="em_rating_code" )
    private String emRatingCode;

    @TableField( value="em_rating_value" )
    private String emRatingValue;

    @TableField( value="em_rating_name" )
    private String emRatingName;

    @TableField( value="pdf_url" )
    private String pdfUrl;

}
