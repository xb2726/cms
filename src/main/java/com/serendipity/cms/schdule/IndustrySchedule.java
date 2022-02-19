package com.serendipity.cms.schdule;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.serendipity.cms.entity.dto.IndustryDTO;
import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.service.IIndustryService;
import com.serendipity.cms.utils.FetchUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther bin
 */
@Component
@Slf4j
public class IndustrySchedule {

    @Autowired
    private IIndustryService industryService;

    @Scheduled(cron = "0 */5 * *  * ?")
    public void pullIndustryList() throws IOException {
        int pageNo=1;
        String database="6767533";
        while (true){
            IndustryDTO fetch = (IndustryDTO) FetchUtils.fetch(pageNo,database,false);
            Integer hits = fetch.getHits();
            List<Industry> data = fetch.getData();
            boolean flag=false;
            for (Industry datum : data) {
                datum.setPdfUrl("https://pdf.dfcfw.com/pdf/H3_"+datum.getInfoCode()+"_1.pdf");
                // 判断 数据库是否存在
                IndustryQueryDTO dto = new IndustryQueryDTO();
                dto.setInfoCode(datum.getInfoCode());
                List<Industry> industries = industryService.query(dto);

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
                industryService.save(datum);
            }
            if(flag) break;
            if(hits<=pageNo*50) break;
            pageNo++;
        }
    }
}
