package com.serendipity.base.service;

import com.serendipity.base.entity.dto.LoginDTO;
import com.serendipity.base.entity.dto.UserQueryDTO;
import com.serendipity.base.entity.po.User;
import com.serendipity.base.entity.vo.LoginVo;
import com.serendipity.core.domain.PagedDTO;

import java.util.List;

public interface IUserService {

    int save(User user);

    void update(User user);

    int delete(String... ids);

    User findById(String id);

    List<User>  query(UserQueryDTO dto);

    PagedDTO<User> pageQuery(UserQueryDTO dto, Integer pageNum, Integer pageSize);

    /**
     * 用户登录
     * @param dto
     */
    LoginVo login(LoginDTO dto);

    int logout(String id);
}
