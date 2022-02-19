package com.serendipity.base.rest.management;

import com.serendipity.base.entity.dto.LoginDTO;
import com.serendipity.base.service.IUserService;
import com.serendipity.core.domain.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther bin
 */
@RestController("LoginRest")
@RequestMapping("manage/login")
@Api(tags = "登录")
public class LoginRest {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "")
    @ApiOperation(value = "用户名、密码登录", response = LoginDTO.class)
    public ResultDTO<String> findById(@Valid LoginDTO dto) {
        String token = userService.login(dto);
        return ResultDTO.success(token);
    }

}
