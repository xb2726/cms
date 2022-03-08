package com.serendipity.base.rest.management;

import com.serendipity.base.entity.dto.CommonDTO;
import com.serendipity.base.entity.dto.LoginDTO;
import com.serendipity.base.entity.vo.LoginVo;
import com.serendipity.base.service.IUserService;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther bin
 */
@RestController("LoginRest")
@RequestMapping("manage/login")
@Api(tags = "登录")
public class LoginRest extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "")
    @ApiOperation(value = "用户名、密码登录", response = LoginVo.class)
    public ResultDTO<LoginVo> login(@Valid LoginDTO dto) {
        return ResultDTO.success(userService.login(dto));
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "退出登录")
    public ResultDTO<Integer> logout() {
        return ResultDTO.success(userService.logout(currentId()));
    }

}
