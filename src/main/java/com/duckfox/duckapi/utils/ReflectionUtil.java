package com.duckfox.duckapi.utils;

public class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static <T> T getFieldValue(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);

            return (T) field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> void setFieldValue(Object obj, String fieldName, T value) {

        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);

            field.setAccessible(true);

            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
