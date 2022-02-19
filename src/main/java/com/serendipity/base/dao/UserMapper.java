package com.serendipity.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.serendipity.base.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther bin
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
