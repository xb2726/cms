package com.serendipity.base.rest.manage;

import javax.validation.Valid;

import com.serendipity.base.entity.dto.SysDepQueryDTO;
import com.serendipity.base.entity.po.SysDepPO;
import java.util.List;
import com.serendipity.base.service.ISysDepService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.web.base.BaseController;
import com.serendipity.web.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 部门
 * @Author: bin
 * @Date:   2022-03-09
 */
@Slf4j
@Api("部门")
@RestController
@RequestMapping("/base/sysDep")
public class SysDepRest extends BaseController {
	@Autowired
	private ISysDepService sysDepService;

	@ApiOperation("保存")
    @PostMapping(value = "")
	public ResultDTO<Integer> save(@RequestBody @Valid SysDepPO po) {
         return success(sysDepService.save(po));
    }

	 @PutMapping(value = "")
     @ApiOperation(value = "更新", notes = "更新成功后返回true")
     public ResultDTO update(@RequestBody @Valid SysDepPO po) {
         sysDepService.update(po);
         return success();
     }

     @GetMapping(value = "", params = "id")
     @ApiOperation(value = "根据ID查询", response = SysDepPO.class)
     public ResultDTO<SysDepPO> findById(String id) {
         return success(sysDepService.findById(id));
     }


     @GetMapping(value = "/list")
     @ApiOperation(value = "列表查询", response = SysDepPO.class)
     public ResultDTO<List<SysDepPO>> query(@Valid SysDepQueryDTO dto) {
         return success(sysDepService.query(dto));
     }

     @GetMapping(value = "/page")
     @ApiOperation(value = "分页查询", response = SysDepPO.class)
     public ResultDTO<PagedDTO<SysDepPO>> pageQuery(@Valid PageVO pageVO, @Valid SysDepQueryDTO dto) {
         PagedDTO<SysDepPO> PagedDTO = sysDepService.pageQuery(dto, pageVO.getPageNum(), pageVO.getPageSize());
         return success(PagedDTO);
     }

}
