package com.binx.core.domain;

import cn.hutool.core.convert.Convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PagedDTO<T>  implements Serializable {
    private Integer current;
    private Integer size;
    private Long total;
    private List<T> records;

    public PagedDTO() {
        this(0L, new ArrayList(), 0, 0);
    }

    public PagedDTO(long total, List<T> data) {
        this(total, data, 0, 0);
    }

    public PagedDTO(long total, List<T> data, int page, int size) {
        this.current = page;
        this.size = size;
        this.total = total;
        this.records = data;
    }

    public static <T> PagedDTO<T> paged(List<T> list, long total, int page, int size) {
        return new PagedDTO(total, list, page, size);
    }

    public static <T> PagedDTO<T> paged(List<T> list, long total) {
        return paged(list, total, 0, 0);
    }

    public static <T> PagedDTO<T> paged(List<T> list) {
        return paged(list, (long)list.size(), 0, 0);
    }

    public int getPages() {
        return this.size != null && this.size > 0 && this.total != null && this.total > 0L ? Convert.toInt(Math.ceil((double)this.total / (double)this.size)) : 0;
    }

    public boolean getNext() {
        if (this.current != null && this.current > 0 && this.getPages() > 0) {
            return this.getPages() > this.current;
        } else {
            return false;
        }
    }

    public boolean getPrev() {
        if (this.current != null && this.current > 0 && this.getPages() > 0) {
            return this.current > 1;
        } else {
            return false;
        }
    }

    public Integer getCurrent() {
        return this.current;
    }

    public Integer getSize() {
        return this.size;
    }

    public Long getTotal() {
        return this.total;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public String toString() {
        return "PagedDTO(current=" + this.getCurrent() + ", size=" + this.getSize() + ", total=" + this.getTotal() + ", records=" + this.getRecords() + ")";
    }
}
