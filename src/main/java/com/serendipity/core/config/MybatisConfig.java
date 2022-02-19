package com.serendipity.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther bin  通过自带add和update的时候，自动填充字段。
 */
@Component
public class MybatisConfig implements MetaObjectHandler {
    // TODO 还有一些字段需要后面把threadLocal写好后在填充

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("create_date",new Date(),metaObject);
        this.setFieldValByName("deleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("update_date",new Date(),metaObject);
    }
}
