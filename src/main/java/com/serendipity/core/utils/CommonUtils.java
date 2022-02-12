package com.serendipity.core.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.serendipity.core.enums.ValueEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther X .
 */
public class CommonUtils {
    public static boolean isEmpty(Object value) {
        return null == value ? true : ObjectUtil.isEmpty(value);
    }

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }

    public static String unEscape(Object value) {
        if (value == null) {
            return null;
        } else {
            return value instanceof ValueEnum ? ((ValueEnum)value).getValue().toString() : value.toString();
        }
    }

    public static <T> T toBean(Object source, Class<T> clazz) {
        if (null == source) {
            return null;
        } else {
            new CopyOptions();
            return BeanUtil.toBean(source, clazz);
        }
    }

    public static <T> T toBean(Object source, Class<T> clazz, CopyOptions copyOptions) {
        return null == source ? null : BeanUtil.toBean(source, clazz, copyOptions);
    }

    public static <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz) {
        if (ObjectUtil.isEmpty(sourceList)) {
            return new ArrayList();
        } else {
            List<T> list = new ArrayList();
            Iterator var3 = sourceList.iterator();

            while(var3.hasNext()) {
                TS item = (TS) var3.next();
                list.add(toBean(item, clazz));
            }

            return list;
        }
    }

    public static <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz, CopyOptions copyOptions) {
        if (ObjectUtil.isEmpty(sourceList)) {
            return new ArrayList();
        } else {
            List<T> list = new ArrayList();
            Iterator var4 = sourceList.iterator();

            while(var4.hasNext()) {
                TS item = (TS) var4.next();
                list.add(toBean(item, clazz, copyOptions));
            }

            return list;
        }
    }


}
