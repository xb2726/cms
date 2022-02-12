package com.serendipity.web.base;

import cn.hutool.core.bean.copier.CopyOptions;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.core.enums.ResultCode;
import com.serendipity.core.exception.BusinessException;
import com.serendipity.core.utils.JsonUtils;
import com.serendipity.utils.CommonUtils;
import com.serendipity.web.config.BaseProperties;
import com.serendipity.web.security.AuthContext;
import com.serendipity.web.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    @Resource
    protected BaseProperties baseConfig;

    protected HttpServletRequest getRequest() {
        return AuthContext.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return AuthContext.getResponse();
    }

    protected String getBody() {
        return this.getBody(Charset.defaultCharset());
    }

    protected String getBody(Charset charset) {
        byte[] buffer = AuthContext.getBody();
        return buffer == null ? null : new String(buffer, charset);
    }

    protected <T> T fromBody(Class<T> clazz) {
        return this.fromBody(clazz, Charset.defaultCharset());
    }

    protected <T> T fromBody(Class<T> clazz, Charset charset) {
        String body = this.getBody(charset);
        return body == null ? null : JsonUtils.json(body, clazz);
    }

    protected String getHeader(String key) {
        HttpServletRequest request = this.getRequest();
        return request.getHeader(key);
    }

    protected Token currentRequiredToken() {
        Token token = this.currentToken();
        if (token == null) {
            throw new BusinessException(ResultCode.UN_AUTHORIZED);
        } else {
            return token;
        }
    }

    protected Token currentToken() {
        return AuthContext.getToken(this.baseConfig);
    }

    protected String currentId() {
        Token token = this.currentToken();
        return token != null && !CommonUtils.isEmpty(token.getId()) ? token.getId() : null;
    }

    protected String currentRequiredId() {
        String id = this.currentId();
        if (CommonUtils.isEmpty(id)) {
            throw new BusinessException(ResultCode.UN_AUTHORIZED);
        } else {
            return id;
        }
    }

    protected Integer currentIdAsInteger() {
        Token token = this.currentToken();
        return token != null && !CommonUtils.isEmpty(token.getId()) ? token.getIdAsInteger() : null;
    }

    protected Integer currentRequiredIdAsInteger() {
        Integer id = this.currentIdAsInteger();
        if (id != null && id != 0) {
            return id;
        } else {
            throw new BusinessException(ResultCode.UN_AUTHORIZED);
        }
    }

    protected Integer currentCommunityId() {
        Token token = this.currentToken();
        return token == null ? null : (Integer)token.getClaimValue("communityId", Integer.class);
    }

    protected Integer currentHouseId() {
        Token token = this.currentToken();
        return token == null ? null : (Integer)token.getClaimValue("houseId", Integer.class);
    }

    protected <T> ResultDTO<T> success() {
        return ResultDTO.success();
    }

    protected <T> ResultDTO<T> success(T data) {
        return ResultDTO.success(data);
    }

    protected <T> ResultDTO<T> fail(ResultCode resultCode) {
        return ResultDTO.fail(resultCode);
    }

    protected <T> ResultDTO<T> fail(String message) {
        return ResultDTO.fail(ResultCode.FAILURE.getCode(), message);
    }

    protected <T> ResultDTO<T> fail(String message, int code) {
        return ResultDTO.fail(code, message);
    }

    protected <T> T toBean(Object source, Class<T> clazz) {
        return CommonUtils.toBean(source, clazz);
    }

    protected <T> T toBean(Object source, Class<T> clazz, CopyOptions copyOptions) {
        return CommonUtils.toBean(source, clazz, copyOptions);
    }

    protected <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz) {
        return CommonUtils.toListBean(sourceList, clazz);
    }

    protected <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz, CopyOptions copyOptions) {
        return CommonUtils.toListBean(sourceList, clazz, copyOptions);
    }
}
