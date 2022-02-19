package com.serendipity.cms.service;

import com.serendipity.base.entity.dto.UserQueryDTO;
import com.serendipity.base.entity.po.User;
import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.core.domain.PagedDTO;

import java.util.List;

public interface IIndustryService {

    int save(Industry industry);

    void update(Industry industry);

    int delete(String... ids);

    Industry findById(String id);

    List<Industry> query(IndustryQueryDTO dto);

    PagedDTO<Industry> pageQuery(IndustryQueryDTO dto, Integer pageNum, Integer pageSize);
}
