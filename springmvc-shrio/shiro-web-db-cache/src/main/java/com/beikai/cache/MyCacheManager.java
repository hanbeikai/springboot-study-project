package com.beikai.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 * 自定义 shrio 缓存管理  把权限信息存储进redis中 如果redis中存在,则不从数据库中获取
 * 如果redis中没有 从数据库中获取,存储进redis中
 */
public class MyCacheManager implements CacheManager {

    @Resource
    private MyCache myCache;


    public <K, V> Cache<K, V> getCache(String s) throws CacheException {

        return myCache;
    }
}
