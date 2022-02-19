package com.serendipity.cms.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.serendipity.cms.dao.IndustryMapper;
import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.service.IIndustryService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.extra.query.QueryWrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther bin
 */
@Service
public class IndustryServiceImpl implements IIndustryService {

    @Autowired
    private IndustryMapper industryMapper;

    @Override
    public int save(Industry industry) {
        return industryMapper.insert(industry);
    }

    @Override
    public void update(Industry industry) {
        industryMapper.updateById(industry);
    }

    @Override
    public int delete(String... ids) {
        return industryMapper.deleteBatchIds(ListUtil.toList(ids));
    }

    @Override
    public Industry findById(String id) {
        return industryMapper.selectById(id);
    }

    @Override
    public List<Industry> query(IndustryQueryDTO dto) {
        QueryWrapper<Industry> wrapper = new QueryWrapper<>();
        QueryWrapperUtils.initCondition(dto, wrapper);
        return industryMapper.selectList(wrapper);
    }

    @Override
    public PagedDTO<Industry> pageQuery(IndustryQueryDTO dto, Integer pageNum, Integer pageSize) {
        QueryWrapper<Industry> wrapper = new QueryWrapper<>();
        QueryWrapperUtils.initCondition(dto, wrapper);
        wrapper.orderByDesc("publish_date","id");
        final IPage<Industry> fooPage = new Page(pageNum, pageSize);
        IPage<Industry> dataPage = industryMapper.selectPage(fooPage, wrapper);
        return new PagedDTO<>(dataPage.getTotal(), dataPage.getRecords());
    }
}
