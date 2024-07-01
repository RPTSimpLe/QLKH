package com.DoAn.f88.exeption;

import java.lang.reflect.Field;
import java.util.List;

public class ValidateValueForm {
    public static <T> void validateEntity(T entity) {
        if (entity == null) {
            throw new ValidateException("Vui lòng nhập đầy đủ các trường");
        }

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(entity) == null) {
                    throw new ValidateException("Vui lòng nhập đầy đủ các trường");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
