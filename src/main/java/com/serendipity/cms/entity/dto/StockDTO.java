package com.serendipity.cms.entity.dto;

import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.entity.po.StocksPO;
import com.serendipity.extra.query.Condition;
import com.serendipity.extra.query.MatchMode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
@Getter
@Setter
public class StockDTO {

    private Integer hits;
    private Integer totalPage;
    private List<StocksPO> data;
}
