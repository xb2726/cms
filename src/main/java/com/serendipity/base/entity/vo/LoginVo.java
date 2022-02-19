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
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("JWT-TOKEN")
    private Token token;
}
