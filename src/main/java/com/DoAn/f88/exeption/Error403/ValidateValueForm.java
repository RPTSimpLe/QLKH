package com.DoAn.f88.exeption.Error403;

import com.DoAn.f88.exeption.Error401.AuthException;

import java.lang.reflect.Field;

public class ValidateValueForm {
    public static <T> void validateNull(T entity) {
        if (entity == null) {
            throw new AuthException("Vui lòng nhập đầy đủ các trường");
        }

        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(entity);
                    if (fieldValue == null || (fieldValue instanceof String && ((String) fieldValue).trim().isEmpty())) {
                        throw new AuthException("Vui lòng nhập đầy đủ thông tin");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }


}
