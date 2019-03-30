package com.beikai.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 这是jedis的工具类
 */
@Component
public class JedisUtils {

    @Resource
    private JedisPool jedisPool;

    // 创建获取jedis的方法
    public Jedis getResource(){
        return jedisPool.getResource();
    }

    /**
     * 在redis中添加
     * @param key    关键字
     * @param value  值
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key,value);

            return value;
        } finally {
            jedis.close();
        }
    }

    /**
     * 设置redis的超时时间
     * @param key   关键字
     * @param i    超时时间  单位 秒
     */
    public void expire(byte[] key, int i) {
        Jedis jedis = getResource();
        try {
            jedis.expire(key,i);
        } finally {
            jedis.close();
        }
    }

    /**
     * 通过制定key获取value
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        Jedis jedis = getResource();

        try {
            byte[] bytes = jedis.get(key);
            return bytes;
        } finally {
            jedis.close();
        }
    }

    /**
     * 删除redix中的值
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = getResource();

        try {
            jedis.del(key);

        } finally {
            jedis.close();
        }
    }

    /**
     * 获取所有在活动中的session
     * @param shiro_session_prefix
     * @return
     */
    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis = getResource();

        try {
            return jedis.keys((shiro_session_prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}
