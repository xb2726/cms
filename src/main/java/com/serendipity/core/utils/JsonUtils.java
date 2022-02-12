package com.serendipity.core.utils;

import com.serendipity.core.lang.Func;
import com.serendipity.utils.CommonUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther X .
 */
public class JsonUtils {
    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static String toJson(Object source) {
        try {
            ObjectMapper mapper = getMapper();
            return mapper.writeValueAsString(source);
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static <T> T json(String content, Class<T> clazz) {
        try {
            ObjectMapper mapper = getMapper();
            return mapper.readValue(content, clazz);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> jsonList(String content, Class<T> clazz) {
        try {
            ObjectMapper mapper = getMapper();
            JavaType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return (List)mapper.readValue(content, listType);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <T> T json(String content, Func<JavaType, TypeFactory> func) {
        try {
            ObjectMapper mapper = getMapper();
            return mapper.readValue(content, (JavaType)func.invoke(mapper.getTypeFactory()));
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static JsonNode node(String content) {
        return node(content, (String)null);
    }

    public static JsonNode node(String content, String nodeName) {
        try {
            ObjectMapper mapper = getMapper();
            JsonNode node = mapper.readTree(content);
            if (CommonUtils.isNotEmpty(nodeName)) {
                node = node.findValue(nodeName);
            }

            return node;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <T> T json(String content, String nodeName, Class<T> clazz) {
        JsonNode value = node(content, nodeName);
        return value == null ? null : json(value.toString(), clazz);
    }

    public static <T> List<T> jsonList(String content, String nodeName, Class<T> clazz) {
        JsonNode value = node(content, nodeName);
        return (List)(value == null ? new ArrayList() : jsonList(value.toString(), clazz));
    }
}
