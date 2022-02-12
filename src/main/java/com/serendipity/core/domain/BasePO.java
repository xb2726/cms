package com.serendipity.core.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

/**
 * @Auther X .
 */
public class BasePO {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @TableField(value = "create_date", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private Date createDate;
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;
    @TableField(value = "reserved1", updateStrategy = FieldStrategy.NEVER)
    private String reserved1;
    @TableField(value = "reserved2", updateStrategy = FieldStrategy.NEVER)
    private String reserved2;

    @TableField(value = "deleted", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private Integer deleted;
}
