package com.duckfox.duckapi.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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

    public static URLClassLoader getClassLoader(String url) throws NoSuchMethodException, MalformedURLException, InvocationTargetException, IllegalAccessException {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        URL url1 = new URL("file:" + url);
        method.invoke(classLoader, url1);
        return classLoader;
    }
}
