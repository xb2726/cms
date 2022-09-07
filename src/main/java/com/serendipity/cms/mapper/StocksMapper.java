package com.serendipity.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.serendipity.cms.entity.po.StocksPO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
 @Mapper
public interface StocksMapper extends BaseMapper<StocksPO> {

}
