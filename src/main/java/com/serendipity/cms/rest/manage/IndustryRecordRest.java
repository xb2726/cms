package com.serendipity.cms.rest.manage;

import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.entity.po.IndustryRecordPO;
import com.serendipity.cms.service.IIndustryRecordService;
import com.serendipity.cms.service.IIndustryService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.web.base.BaseController;
import com.serendipity.web.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther bin
 */
@RestController
@RequestMapping("/manage/industryRecord")
@Api("行业研报浏览记录")
@Slf4j
public class IndustryRecordRest extends BaseController {

    @Autowired
    private IIndustryRecordService industryRecordService;

    @ApiOperation("保存")
    @PostMapping(value = "")
    public ResultDTO<Integer> save(@RequestBody @Valid IndustryRecordPO po) {
        po.setUserId(currentRequiredId());
        return success(industryRecordService.save(po));
    }


}
