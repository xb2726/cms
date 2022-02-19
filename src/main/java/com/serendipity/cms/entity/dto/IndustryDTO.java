package com.serendipity.cms.entity.dto;

import com.serendipity.cms.entity.po.Industry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther bin
 */
@Getter
@Setter
public class IndustryDTO {
    private Integer hits;
    private Integer totalPage;
    private List<Industry> data;
}
