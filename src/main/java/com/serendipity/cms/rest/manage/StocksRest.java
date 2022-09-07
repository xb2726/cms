package com.serendipity.com.serendipity.cms.rest.manage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.serendipity.cms.entity.dto.StocksDTO;
import com.serendipity.cms.entity.po.StocksPO;
import com.serendipity.cms.service.IStocksService;
import java.util.Date;

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
 * @Description: 个股
 * @Author: bin
 * @Date:   2022-06-30
 */
@Slf4j
@Api("个股")
@RestController
@RequestMapping("/manage/stocks")
public class StocksRest extends BaseController {
	@Autowired
	private IStocksService stocksService;

	@ApiOperation("保存")
    @PostMapping(value = "")
	public ResultDTO<Integer> save(@RequestBody @Valid StocksPO po) {
         return success(stocksService.save(po));
    }

	 @PutMapping(value = "")
     @ApiOperation(value = "更新", notes = "更新成功后返回true")
     public ResultDTO update(@RequestBody @Valid StocksPO po) {
         stocksService.update(po);
         return success();
     }

     @GetMapping(value = "", params = "id")
     @ApiOperation(value = "根据ID查询", response = StocksPO.class)
     public ResultDTO<StocksPO> findById(String id) {
         return success(stocksService.findById(id));
     }


     @GetMapping(value = "/list")
     @ApiOperation(value = "列表查询", response = StocksPO.class)
     public ResultDTO<List<StocksPO>> query(@Valid StocksDTO dto) {
         return success(stocksService.query(dto));
     }

     @GetMapping(value = "/page")
     @ApiOperation(value = "分页查询", response = StocksPO.class)
     public ResultDTO<PagedDTO<StocksPO>> pageQuery(@Valid PageVO pageVO, @Valid StocksDTO dto) {
         PagedDTO<StocksPO> PagedDTO = stocksService.pageQuery(dto, pageVO.getPageNum(), pageVO.getPageSize());
         return success(PagedDTO);
     }

}
