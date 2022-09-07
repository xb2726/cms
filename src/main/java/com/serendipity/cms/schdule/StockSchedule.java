package com.serendipity.cms.schdule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.dto.StockDTO;
import com.serendipity.cms.entity.dto.StocksDTO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.entity.po.StocksPO;
import com.serendipity.cms.service.IStocksService;
import com.serendipity.cms.utils.FetchUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Auther bin
 */
@Component
@Slf4j
public class StockSchedule {


    @Autowired
    private IStocksService stocksService;

   @Scheduled(cron = "0 */5 * * * ?")
  public void pullStockList() throws IOException {
        int pageNo=1;

        String database="5450805";
      while (true){
          StockDTO fetch = (StockDTO) FetchUtils.fetch(pageNo,database,true);
          List<StocksPO> data = fetch.getData();
          Integer hits = fetch.getHits();
          boolean flag=false;
          for (StocksPO datum : data) {
              datum.setPdfUrl("https://pdf.dfcfw.com/pdf/H3_"+datum.getInfoCode()+"_1.pdf");
              // 判断 数据库是否存在
              StocksDTO dto = new StocksDTO();
              dto.setInfoCode(datum.getInfoCode());
              List<StocksPO> industries = stocksService.query(dto);

              if (CollectionUtil.isNotEmpty(industries)){
                  log.info("********************"+industries.get(0).getInfoCode()+"************************");
                  flag=true;
                  break;
              }
              if(datum.getPublishDate().before(DateUtil.offsetMonth(new Date(),-15))){
                  log.info(DateUtil.offsetMonth(new Date(),-15).toString());
                  flag=true;
                  break;
              }
              stocksService.save(datum);
          }
          if(flag) break;
          if(hits<=pageNo*50) break;
          pageNo++;
      }

  }
}
