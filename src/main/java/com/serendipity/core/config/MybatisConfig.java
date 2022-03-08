package com.serendipity.core.config;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.serendipity.web.security.AuthContext;
import com.serendipity.web.vo.Token;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auther bin  通过自带add和update的时候，自动填充字段。
 */
@Component
public class MybatisConfig implements MetaObjectHandler {
    // TODO 还有一些字段需要后面把threadLocal写好后在填充

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("creator_id",getCurrentUserId(),metaObject);
        this.setFieldValByName("create_date",new Date(),metaObject);
        this.setFieldValByName("deleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updater_id",getCurrentUserId(),metaObject);
        this.setFieldValByName("update_date", new Date(),metaObject);
    }

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    private String getCurrentUserId() {
        if(RequestContextHolder.getRequestAttributes()==null){
            return "1";
        }
        //获取到当前线程绑定的请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//已经拿到session,就可以拿到session中保存的用户信息了。
        Token token =(Token) request.getSession().getAttribute("SD_TOKEN");
        return token.getId();
    }

}
