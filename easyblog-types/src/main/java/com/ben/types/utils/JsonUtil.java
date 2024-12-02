package com.ben.types.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-02  11:37
 * @Description: obj to string
 * @Version: 1.0
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static String toJsonString(Object obj) {
        try {
            return INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // todo Jackson 出参打印包含 Java 8 新日期出错问题
            return obj.toString();
        }
    }

}
