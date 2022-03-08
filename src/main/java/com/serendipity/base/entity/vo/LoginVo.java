package com.serendipity.base.entity.vo;

import com.serendipity.web.vo.Token;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther bin
 */
@Getter
@Setter
public class LoginVo {
    @ApiModelProperty("登录ming")
    private String  loginName;
    @ApiModelProperty("菜单栏")
    private String  emu;
    @ApiModelProperty("TOKEN")
    private String token;
}
