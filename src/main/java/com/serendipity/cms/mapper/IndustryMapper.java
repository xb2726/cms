package com.serendipity.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.po.Industry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther bin
 */
@Mapper
public interface IndustryMapper extends BaseMapper<Industry> {


    List<Industry>  pageQueryRead(@Param("dto") IndustryQueryDTO dto);


    Integer  pageQueryReadTotal(@Param("dto") IndustryQueryDTO dto);
}
