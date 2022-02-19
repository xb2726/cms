package com.serendipity.base.entity.dto;

import com.serendipity.extra.query.Condition;
import com.serendipity.extra.query.MatchMode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther bin
 */
@Getter
@Setter
public class UserQueryDTO {

    @Condition(target = "id",machMode = MatchMode.EQ)
    private String id;
}
