package com.serendipity.base.filter;

import cn.hutool.json.JSONUtil;
import com.serendipity.core.enums.ResultCode;
import com.serendipity.core.exception.BusinessException;
import com.serendipity.core.utils.CommonUtils;
import com.serendipity.core.utils.redis.RedisStringUtil;
import com.serendipity.web.config.BaseProperties;
import com.serendipity.web.security.AuthContext;
import com.serendipity.web.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther bin
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    public static Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
    private final BaseProperties config;
    private final RedisStringUtil redisStringUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("********************服务端请求：{}******************************", request.getRequestURI());
        if (!(handler instanceof HandlerMethod)) {
            return true;
        } else {
          /*  HandlerMethod method = (HandlerMethod)handler;
            YzAuth auth = (YzAuth)method.getMethodAnnotation(YzAuth.class);
            if (auth == null) {
                Class<?> clazz = method.getBeanType();

                for(auth = (YzAuth)clazz.getAnnotation(YzAuth.class); auth == null && Object.class != (clazz = clazz.getSuperclass()); auth = (YzAuth)clazz.getAnnotation(YzAuth.class)) {
                }
            }*/

           /* if (auth != null && !auth.anonymous()) {*/
            log.error(String.format("preHandle.HttpServletRequest:%s", request));
                Token token = AuthContext.getToken(this.config);

            log.error(String.format("token:%s", JSONUtil.toJsonStr(token)));
                if (token != null && !CommonUtils.isEmpty(token.getId())) {
                    // 续签
                    String redisToken = (String)redisStringUtil.get("cms:login:" + token.getId());
                    if(StringUtils.isEmpty(redisToken)){
                        throw new BusinessException(ResultCode.UN_AUTHORIZED);
                    }

                    redisStringUtil.expire("cms:login:" + token.getId(),60*30);
                   /* if (CommonUtils.isNotEmpty(auth.roles())) {
                        String[] roles = token.getRole().split(",");
                        if (!ArrayUtil.containsAny(roles, auth.roles())) {
                            throw new BusinessException(ResultCode.UN_AUTHORIZED);
                        }
                    }*/

                    return super.preHandle(request, response, handler);
                } else {
                    throw new BusinessException(ResultCode.UN_AUTHORIZED);
                }
           /* } else {
                return true;
            }*/
        }
    }

    public AuthInterceptor(BaseProperties config,RedisStringUtil redisStringUtil) {
        this.config = config;
        this.redisStringUtil=redisStringUtil;
    }

}
