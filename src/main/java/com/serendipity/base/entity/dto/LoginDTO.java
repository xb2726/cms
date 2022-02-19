package com.serendipity.base.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Auther bin
 */
@Getter
@Setter
public class LoginDTO {

    @ApiModelProperty("登录ming")
    @NotBlank(message = "登录名不能为空！")
    private String  username;
    @NotBlank(message = "登录密码不能为空！")
    @ApiModelProperty("密码")
    private String password;

}
