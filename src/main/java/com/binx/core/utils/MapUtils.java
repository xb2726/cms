package com.binx.core.utils;



import cn.hutool.core.bean.BeanUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther X .
 */
public class MapUtils {

    public static Map<String, Object> map(Object obj) {
        Map<String, Object> map = new HashMap();
        if (obj == null) {
            return map;
        } else {
            try {
                if (!(obj instanceof Map)) {
                    return BeanUtil.beanToMap(obj);
                } else {
                    Map<?, ?> item = (Map)obj;
                    Iterator var3 = item.keySet().iterator();

                    while(var3.hasNext()) {
                        Object key = var3.next();
                        map.put(key.toString(), item.get(key));
                    }

                    return map;
                }
            } catch (Exception var5) {
                var5.printStackTrace();
                return map;
            }
        }
    }

    public static String toUrl(Map<String, Object> map) {
        return toUrl(map, "", true);
    }

    public static String toUrl(Map<String, Object> map, String charset) {
        return toUrl(map, charset, true);
    }

    public static String toUrl(Map<String, Object> map, String charset, boolean filterEmpty) {
        StringBuilder builder = new StringBuilder();
        Iterator var4 = map.keySet().iterator();

        while(true) {
            String key;
            String value;
            while(true) {
                do {
                    if (!var4.hasNext()) {
                        if (builder.length() > 0) {
                            builder.delete(builder.length() - 1, builder.length());
                        }

                        return builder.toString();
                    }

                    key = (String)var4.next();
                } while(filterEmpty && CommonUtils.isEmpty(key));

                value = CommonUtils.unEscape(map.get(key));
                if (value == null) {
                    if (filterEmpty) {
                        continue;
                    }

                    value = "";
                    break;
                }

                if (CommonUtils.isNotEmpty(charset)) {
                    try {
                        value = URLEncoder.encode(value, charset);
                    } catch (Exception var8) {
                        var8.printStackTrace();
                    }
                }
                break;
            }

            builder.append(String.format("%s=%s&", key, value));
        }
    }
}
