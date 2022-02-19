package com.serendipity.cms.rest.manage;

import com.serendipity.base.entity.dto.UserQueryDTO;
import com.serendipity.base.entity.po.User;
import com.serendipity.cms.entity.dto.IndustryQueryDTO;
import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.service.IIndustryService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.web.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.serendipity.core.domain.ResultDTO.success;

/**
 * @Auther bin
 */
@RestController
@RequestMapping("/manage/industry")
@Api("行业研报")
public class IndustryRest {

    @Autowired
    private IIndustryService industryService;

    @ApiOperation("保存")
    @PostMapping(value = "")
        public ResultDTO<Integer> save(@RequestBody @Valid Industry industry) {
        return success(industryService.save(industry));
    }

    @PutMapping(value = "")
    @ApiOperation(value = "更新", notes = "更新成功后返回true")
    public ResultDTO update(@RequestBody @Valid Industry industry) {
        industryService.update(industry);
        return success();
    }

    @GetMapping(value = "", params = "id")
    @ApiOperation(value = "根据ID查询", response = Industry.class)
    public ResultDTO<Industry> findById(String id) {
        return success(industryService.findById(id));
    }


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询", response = Industry.class)
    public ResultDTO<List<Industry>> query(@Valid IndustryQueryDTO dto) {
        return success(industryService.query(dto));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询", response = Industry.class)
    public ResultDTO<PagedDTO<Industry>> pageQuery(@Valid PageVO pageVO, @Valid IndustryQueryDTO dto) {
        PagedDTO<Industry> userPagedDTO = industryService.pageQuery(dto, pageVO.getPageNum(), pageVO.getPageSize());
        return success(userPagedDTO);
    }
}
