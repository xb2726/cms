package com.serendipity.base.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.HexUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.serendipity.base.dao.UserMapper;
import com.serendipity.base.entity.dto.LoginDTO;
import com.serendipity.base.entity.dto.UserQueryDTO;
import com.serendipity.base.entity.po.User;
import com.serendipity.base.entity.vo.LoginVo;
import com.serendipity.base.service.IUserService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.core.utils.redis.RedisHashUtil;
import com.serendipity.core.utils.redis.RedisStringUtil;
import com.serendipity.extra.query.Assert;
import com.serendipity.extra.query.QueryWrapperUtils;
import com.serendipity.web.config.BaseProperties;
import com.serendipity.web.vo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther bin
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BaseProperties properties;

    @Autowired
    private RedisStringUtil redisStringUtil;

    @Override
    public int save(User user) {
        // 设置默认密码
        user.setPassWord(HexUtil.encodeHexStr("123ABC"));
        return userMapper.insert(user);
    }

    @Override
    public void update(User user) {

        userMapper.updateById(user);
    }

    @Override
    public int delete(@NotEmpty String... ids) {
        return userMapper.deleteBatchIds(ListUtil.toList(ids));
    }

    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> query(UserQueryDTO dto) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapperUtils.initCondition(dto, wrapper);
        return userMapper.selectList(wrapper);
    }

    @Override
    public PagedDTO<User> pageQuery(UserQueryDTO dto, Integer pageNum, Integer pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapperUtils.initCondition(dto, wrapper);
        final IPage<User> fooPage = new Page(pageNum, pageSize);
        IPage<User> dataPage = userMapper.selectPage(fooPage, wrapper);
        return new PagedDTO<>(dataPage.getTotal(), dataPage.getRecords());
    }

    @Override
    public LoginVo login(LoginDTO dto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, dto.getUsername()).eq(User::getPassWord,  HexUtil.encodeHexStr(dto.getPassword()));
        User user = userMapper.selectOne(queryWrapper);
        Assert.isNotNull(user,"用户名或密码错误！");
        Assert.isFalse(user.getLocked()==1,"该用户状态异常！请联系管理人员。");

        Token token = new Token();
        token.setId(user.getId());
        token.setName(user.getName());
        String jwt_token = token.jwt(properties.getJwtSecret());
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(jwt_token);
        loginVo.setLoginName(user.getLoginName());
       redisStringUtil.set("cms:login:"+user.getId(),jwt_token,60*30);
        return  loginVo;

    }

    @Override
    public int logout(String id) {
        redisStringUtil.del("cms:login:"+ id);
        return 1;
    }
}
