package com.pig.rpc.factory;

import lombok.SneakyThrows;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Mr.Pan
 * @Date 2025/2/22
 **/
public class SingletonFactory {
    private static final Map<Class<?>, Object> INSTANCE_CACHE = new ConcurrentHashMap<>();

    private SingletonFactory() {
    }

    @SneakyThrows
    public static <T> T getInstance(Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            throw new IllegalArgumentException("clazz不能为null");
        }

        if (INSTANCE_CACHE.containsKey(clazz)) {
            return clazz.cast(INSTANCE_CACHE.get(clazz));
        }

        // 双重检测锁
        synchronized (SingletonFactory.class) {
            if (INSTANCE_CACHE.containsKey(clazz)) {
                return clazz.cast(INSTANCE_CACHE.get(clazz));
            }

            T t = clazz.getConstructor().newInstance();
            INSTANCE_CACHE.put(clazz, t);

            return t;
        }
    }
}
