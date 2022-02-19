package com.serendipity.base.entity.po;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.serendipity.base.emue.Gender;
import com.serendipity.core.domain.BasePO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Auther bin
 */
@Getter
@Setter
@TableName("sys_user")
public class User extends BasePO {

    @TableField(value = "name")
    private String name;
    @TableField(value = "code")
    private String code;
    @TableField(value = "login_name")
    private String loginName;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "password",updateStrategy = FieldStrategy.NEVER)
    private String passWord;
    @TableField(value = "gender")
    private Gender gender;
    @TableField(value = "birth_day")
    private Date birthDay;
    @TableField(value = "address")
    private String address;
    @TableField(value = "photo_url")
    private String photoUrl;
    @TableField(value = "education")
    private String education;
    @TableField(value = "graduated_school")
    private String graduatedSchool;
    @TableField(value = "org_id")
    private String orgId;
    @TableField(value = "org_name")
    private String orgName;
    @TableField(value = "entry_time")
    private String entryTime;
    @TableField(value = "resign_time")
    private String resignTime;
    @TableField(value = "remark")
    private String remark;
    @TableField(value = "locked")
    private Integer locked;
}
