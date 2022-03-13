package com.serendipity.base.service;

import com.serendipity.base.entity.dto.SysDepQueryDTO;
import com.serendipity.base.entity.po.SysDepPO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.core.domain.PagedDTO;

;import java.util.List;

/**
 * @Description: 部门
 * @Author: bin
 * @Date:   2022-03-09
 */
public interface ISysDepService {

    int save(SysDepPO po);

    void update(SysDepPO po);

    int delete(String... ids);

    SysDepPO findById(String id);

    List<SysDepPO> query(SysDepQueryDTO dto);

    PagedDTO<SysDepPO> pageQuery(SysDepQueryDTO dto, Integer pageNum, Integer pageSize);
}
