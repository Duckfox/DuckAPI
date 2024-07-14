package com.duckfox.duckapi.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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

    public static <T> List<Class<? extends T>> findClasses(File file, Class<T> clazz) throws IOException, ClassNotFoundException {
        // 借鉴PlaceholderAPI插件中的me.clip.placeholderapi.util.FileUtil.findClass
        if (!file.exists()) {
            return null;
        } else {
            // 获取url
            URL jar = file.toURI().toURL();
            // 获取类加载器
            URLClassLoader loader = new URLClassLoader(new URL[]{jar}, clazz.getClassLoader());
            // 列表储存class字符串
            List<String> matches = new ArrayList<>();
            // 最终的class列表
            List<Class<? extends T>> classes = new ArrayList<>();
            JarInputStream stream = new JarInputStream(jar.openStream());
            Throwable var7 = null;

            try {
                JarEntry entry;
                while ((entry = stream.getNextJarEntry()) != null) {
                    String name = entry.getName();
                    if (name.endsWith(".class")) {
                        matches.add(name.substring(0, name.lastIndexOf(46)).replace('/', '.'));
                    }
                }

                for (String match : matches) {
                    try {
                        Class<?> loaded = loader.loadClass(match);
                        if (clazz.isAssignableFrom(loaded)) {
                            classes.add(loaded.asSubclass(clazz));
                        }
                    } catch (NoClassDefFoundError ignored) {
                    }
                }
            } catch (Throwable var21) {
                var7 = var21;
                throw var21;
            } finally {
                loader.close();
                if (var7 != null) {
                    try {
                        stream.close();
                    } catch (Throwable var19) {
                        var7.addSuppressed(var19);
                    }
                } else {
                    stream.close();
                }

            }

            return classes;
        }
    }


}
