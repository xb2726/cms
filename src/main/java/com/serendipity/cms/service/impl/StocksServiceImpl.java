package com.serendipity.cms.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.serendipity.cms.entity.dto.StocksDTO;
import com.serendipity.cms.entity.po.StocksPO;
import com.serendipity.cms.mapper.StocksMapper;
import com.serendipity.cms.service.IStocksService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.extra.query.MatchMode;
import com.serendipity.extra.query.QueryWrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
@Service
public class StocksServiceImpl  implements IStocksService {
        @Autowired
        private StocksMapper stocksMapper;

        @Override
        public int save(StocksPO po) {
            return stocksMapper.insert(po);
        }

        @Override
        public void update(StocksPO po) {
            stocksMapper.updateById(po);
        }

        @Override
        public int delete(String... ids) {
            return stocksMapper.deleteBatchIds(ListUtil.toList(ids));
        }

        @Override
        public StocksPO findById(String id) {
            return stocksMapper.selectById(id);
        }

        @Override
        public List<StocksPO> query(StocksDTO dto) {
            QueryWrapper<StocksPO> wrapper = new QueryWrapper<>();
            QueryWrapperUtils.initCondition(dto, wrapper);
            return stocksMapper.selectList(wrapper);
        }

        @Override
        public PagedDTO<StocksPO> pageQuery(StocksDTO dto, Integer pageNum, Integer pageSize) {
            QueryWrapper<StocksPO> wrapper = new QueryWrapper<>();
            QueryWrapperUtils.initCondition(dto, wrapper);
            wrapper.orderByDesc("PUBLISH_DATE");
            wrapper.orderByDesc("id");
            final IPage<StocksPO> fooPage = new Page(pageNum, pageSize);
            IPage<StocksPO> dataPage = stocksMapper.selectPage(fooPage, wrapper);
            return new PagedDTO<>(dataPage.getTotal(), dataPage.getRecords());
        }
}
