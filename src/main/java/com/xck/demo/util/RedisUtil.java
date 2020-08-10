package com.xck.demo.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author xck
 * @date 2020/5/28 0:04
 * @deprecated redis工具类
 */
public class RedisUtil {

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String,Object> redisTemplate = SpringUtils.getBean("redisTemplate");

    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public static boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    public static boolean expire(final String key,final long timeout){
        Boolean result = redisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        return result != null && result;
    }

    public static Object get(String key){

        if (null != redisTemplate.hasKey(key)){
            return redisTemplate.opsForValue().get(key);
        }
        else {
            return null;
        }
    }

    public static Object delete(String key){
        if (null != redisTemplate.hasKey(key)){
            return redisTemplate.delete(key);
        }
        else {
            return null;
        }
    }

    public static void set(final String key,final Object value,final long time){
        redisTemplate.opsForValue().set(key,value,time,TimeUnit.MILLISECONDS);
    }

    public static void set(final String key,final Object value,final long timeout,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeout, timeUnit);
    }
}
