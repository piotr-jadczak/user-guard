package com.pj.userguard.util;

import org.springframework.data.util.ReflectionUtils;

public class DataUtils {

    private DataUtils() {}

    public static <T> void setId(T entity, Class<T> clazz, Long id) {
        var field = ReflectionUtils.findRequiredField(clazz, "id");
        ReflectionUtils.setField(field, entity, id);
    }
}
