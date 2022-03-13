package com.serendipity.cms.entity.dto;

import com.serendipity.extra.query.Condition;
import com.serendipity.extra.query.MatchMode;
import com.serendipity.web.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther bin
 */
@Getter
@Setter
public class IndustryQueryDTO extends PageVO {

    @Condition(target = "title",machMode = MatchMode.LIKE)
    @ApiModelProperty(value = "标题")
    private String title;

    @Condition(target = "info_code")
    private String infoCode;

    private String userId;

}
