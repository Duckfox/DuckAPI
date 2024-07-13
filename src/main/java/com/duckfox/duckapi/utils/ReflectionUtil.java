package com.duckfox.duckapi.utils;

import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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

    public static List<Class<?>> getClasses(String jarFilePath, Class<?>... superClasses) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            File jarFile = new File(jarFilePath);
            URL jarURL = jarFile.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL}, ClassLoader.getSystemClassLoader());

            JarFile jar = new JarFile(jarFilePath);
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class")) {
                    String className = entryName.substring(0, entryName.length() - 6).replace("/", ".");
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (superClasses != null && superClasses.length > 0) {
                            for (Class<?> aClass : superClasses) {
                                if (aClass != null && aClass.isAssignableFrom(clazz)) {
                                    classes.add(clazz);
                                }
                            }
                        } else {
                            classes.add(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }


}
