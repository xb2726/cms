package com.serendipity.base.emue;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;

public enum Gender implements IEnum<Integer> {
    MALE("男",1),
    FEMALE("女",2),
    UNKNOWN("未知",0);

    @EnumValue
    final int value;
    final String name;

    Gender(String name, int value) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

}
