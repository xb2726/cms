package com.serendipity.cms.service.impl;

import com.serendipity.cms.mapper.IndustryMapper;
import com.serendipity.cms.entity.po.IndustryRecordPO;
import com.serendipity.cms.mapper.IndustryRecordMapper;
import com.serendipity.cms.service.IIndustryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 行业研报浏览记录
 * @Author: jeecg-boot
 * @Date:   2022-03-06
 */
@Service
public class IndustryRecordServiceImpl  implements IIndustryRecordService {

    @Autowired
    private IndustryRecordMapper industryRecordMapper;
    @Override
    public int save(IndustryRecordPO industryRecordPO) {
        return  industryRecordMapper.insert(industryRecordPO);
        // 继承 提子  车厘子
    }
}
