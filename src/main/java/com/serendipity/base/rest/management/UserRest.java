package com.serendipity.base.rest.management;

import com.serendipity.base.entity.dto.CommonDTO;
import com.serendipity.base.entity.dto.UserQueryDTO;
import com.serendipity.base.entity.po.User;
import com.serendipity.base.service.IUserService;
import com.serendipity.core.domain.PagedDTO;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.web.base.BaseController;
import com.serendipity.web.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Auther bin
 */
@RestController("UserRest")
@RequestMapping("manage/user")
@Api(tags = "用户")
public class UserRest extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation("保存")
    @PostMapping(value = "")
    public ResultDTO<Integer> save(@RequestBody @Valid User user) {
        return success(userService.save(user));
    }

    @PutMapping(value = "")
    @ApiOperation(value = "更新", notes = "更新成功后返回true")
    public ResultDTO update(@RequestBody @Valid User user) {
        userService.update(user);
        return success();
    }

    @GetMapping(value = "", params = "id")
    @ApiOperation(value = "根据ID查询", response = User.class)
    public ResultDTO<User> findById(String id) {
        return ResultDTO.success(userService.findById(id));
    }


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询", response = User.class)
    public ResultDTO<List<User>> query(@Valid UserQueryDTO dto) {
        return ResultDTO.success(userService.query(dto));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询", response = User.class)
    public ResultDTO<PagedDTO<User>> pageQuery(@Valid PageVO pageVO, @Valid UserQueryDTO dto) {
        PagedDTO<User> userPagedDTO = userService.pageQuery(dto, pageVO.getPageNum(), pageVO.getPageSize());
        return ResultDTO.success(userPagedDTO);
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "删除")
    public ResultDTO<Integer> forceDeleteByIds(@RequestBody @Valid CommonDTO dto) {
        int data = userService.delete(dto.getIds());
        return ResultDTO.success(data);
    }

}
