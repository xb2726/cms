package com.binx.web.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.binx.core.utils.CommonUtils;
import com.binx.core.utils.JsonUtils;
import com.binx.web.config.BaseProperties;
import com.binx.web.vo.Token;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther X .
 */
public class AuthContext {

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes instanceof ServletRequestAttributes ? ((ServletRequestAttributes)requestAttributes).getRequest() : null;
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes instanceof ServletRequestAttributes ? ((ServletRequestAttributes)requestAttributes).getResponse() : null;
    }

    public static String getRequestHeader(String headerName) {
        HttpServletRequest request = getRequest();
        return request == null ? null : request.getHeader(headerName);
    }

    public static byte[] getBody() {
        String var0 = "YZ_BODY";

        try {
            HttpServletRequest request = getRequest();
            if (request == null) {
                return null;
            } else {
                Object body = request.getAttribute("YZ_BODY");
                if (body != null) {
                    return (byte[])((byte[])body);
                } else {
                    ServletInputStream stream = request.getInputStream();
                    if (stream != null && !stream.isFinished()) {
                        byte[] buffer = IoUtil.readBytes(stream);
                        request.setAttribute("YZ_BODY", buffer);
                        return buffer;
                    } else {
                        return null;
                    }
                }
            }
        } catch (IOException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Token getToken(BaseProperties baseConfig) {
        String tokenKey = "YZ_TOKEN";
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        } else {
            Object token = request.getAttribute("YZ_TOKEN");
            if (token != null) {
                return (Token)token;
            } else {
                String headerToken = request.getHeader(baseConfig.getTokenKey());
                String jwt;
                if (StrUtil.isEmpty(headerToken)) {
                    jwt = request.getHeader(baseConfig.getJwtTokenKey());
                    if (CommonUtils.isNotEmpty(jwt)) {
                         token = Token.verify(jwt, baseConfig.getJwtSecret());
                        getRequest().setAttribute("YZ_TOKEN", token);
                        return (Token)token;
                    } else {
                        return null;
                    }
                } else {
                    try {
                        jwt = Base64.decodeStr(headerToken);
                        token = JsonUtils.json(jwt, Token.class);
                        request.setAttribute("YZ_TOKEN", token);
                        return (Token)token;
                    } catch (Exception var6) {
                        var6.printStackTrace();
                        return null;
                    }
                }
            }
        }
    }
}
