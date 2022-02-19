package com.serendipity.core.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther X .
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePO implements Serializable {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    @TableField(value = "create_date", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private Date createDate;
    @TableField(value = "creator_id", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private String creatorId;
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;
    @TableField(value = "updater_id", fill = FieldFill.INSERT_UPDATE, updateStrategy = FieldStrategy.NEVER)
    private String updaterId;
    @TableField(value = "reserved1", updateStrategy = FieldStrategy.NEVER)
    private String reserved1;
    @TableField(value = "reserved2", updateStrategy = FieldStrategy.NEVER)
    private String reserved2;
    @TableField(value = "deleted", fill = FieldFill.INSERT, updateStrategy = FieldStrategy.NEVER)
    private Integer deleted;
}
