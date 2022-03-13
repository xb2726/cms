package com.serendipity.cms.rest.manage;

import com.serendipity.cms.entity.po.Industry;
import com.serendipity.cms.entity.vo.HotVo;
import com.serendipity.cms.service.IHomeService;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Auther bin
 */

@RestController
@RequestMapping("/manage/home")
@Api("首页")
@Slf4j
public class HomeRest extends BaseController {


    @Autowired
    private IHomeService homeService;
    @GetMapping(value = "", params = "id")
    @ApiOperation(value = "根据ID查询", response = Industry.class)
    public ResultDTO<List<HotVo>> getBaiduHot() throws IOException {
        return success(homeService.baiduHot());
    }
}
