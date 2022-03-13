package com.serendipity.base.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.serendipity.base.entity.dto.SysDepQueryDTO;
import com.serendipity.base.entity.po.SysDepPO;
import com.serendipity.base.mapper.SysDepMapper;
import com.serendipity.base.service.ISysDepService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.extra.query.QueryWrapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 部门
 * @Author: bin
 * @Date:   2022-03-09
 */
@Service
public class SysDepServiceImpl implements ISysDepService {
        @Autowired
        private SysDepMapper sysDepMapper;

        @Override
        public int save(SysDepPO po) {
            return sysDepMapper.insert(po);
        }

        @Override
        public void update(SysDepPO po) {
            sysDepMapper.updateById(po);
        }

        @Override
        public int delete(String... ids) {
            return sysDepMapper.deleteBatchIds(ListUtil.toList(ids));
        }

        @Override
        public SysDepPO findById(String id) {
            return sysDepMapper.selectById(id);
        }

        @Override
        public List<SysDepPO> query(SysDepQueryDTO dto) {
            QueryWrapper<SysDepPO> wrapper = new QueryWrapper<>();
            QueryWrapperUtils.initCondition(dto, wrapper);
            return sysDepMapper.selectList(wrapper);
        }

        @Override
        public PagedDTO<SysDepPO> pageQuery(SysDepQueryDTO dto, Integer pageNum, Integer pageSize) {
            QueryWrapper<SysDepPO> wrapper = new QueryWrapper<>();
            QueryWrapperUtils.initCondition(dto, wrapper);
            wrapper.orderByDesc("id");
            final IPage<SysDepPO> fooPage = new Page(pageNum, pageSize);
            IPage<SysDepPO> dataPage = sysDepMapper.selectPage(fooPage, wrapper);
            return new PagedDTO<>(dataPage.getTotal(), dataPage.getRecords());
        }
}
