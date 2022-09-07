package com.serendipity.cms.service;

import com.serendipity.cms.entity.dto.StocksDTO;
import com.serendipity.cms.entity.po.StocksPO;
import com.serendipity.core.domain.PagedDTO;

import java.util.List;

/**
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
public interface IStocksService  {

    int save(StocksPO po);

    void update(StocksPO po);

    int delete(String... ids);

    StocksPO findById(String id);

    List<StocksPO> query(StocksDTO dto);

    PagedDTO<StocksPO> pageQuery(StocksDTO dto, Integer pageNum, Integer pageSize);
}
