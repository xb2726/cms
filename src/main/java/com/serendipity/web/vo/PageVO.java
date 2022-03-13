package com.serendipity.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @Auther bin
 */
@ApiModel
public class PageVO implements Serializable {

    @ApiModelProperty("页码(默认：1)")
    @Min(
            value = 1L,
            message = "页码必须大于0"
    )
    private Integer pageNum;
    @ApiModelProperty("分页大小(默认：10)")
    @Min(
            value = 1L,
            message = "分页大小必须大于0"
    )
    private Integer pageSize;

    public PageVO() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public PageVO(int page, int size) {
        this.pageNum = page;
        this.pageSize = size;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "PageVO(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ")";
    }
}
