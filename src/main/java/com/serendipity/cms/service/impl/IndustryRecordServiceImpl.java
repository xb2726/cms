package com.serendipity.cms.service.impl;

import com.serendipity.cms.mapper.IndustryMapper;
import com.serendipity.cms.entity.po.IndustryRecordPO;
import com.serendipity.cms.mapper.IndustryRecordMapper;
import com.serendipity.cms.service.IIndustryRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 行业研报浏览记录
 * @Author: jeecg-boot
 * @Date:   2022-03-06
 */
@Service
@Slf4j
public class IndustryRecordServiceImpl  implements IIndustryRecordService {

    @Autowired
    private IndustryRecordMapper industryRecordMapper;
    @Override
    public int save(IndustryRecordPO industryRecordPO) {
        try {
            return  industryRecordMapper.insert(industryRecordPO);
        }catch (Exception e){
           // throw new RuntimeException(e.getMessage());
            log.error("重复数据。跳过insert");
            return 1;
        }

    }
}
