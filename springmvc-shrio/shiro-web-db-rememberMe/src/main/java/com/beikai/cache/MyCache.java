package com.beikai.cache;

import com.beikai.utils.JedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * 自定义cache对象
 */
@Component
public class MyCache<K,V> implements Cache<K,V> {

    private final String CACHE_PREFIX = "beikai-cache : ";

    @Resource
    private JedisUtils jedisUtils;

    /**
     * 把key 转换成字节数组的方法
     * @param k
     * @return
     */
    public byte[] getByte(K k){
        // 如果k 是 string的 实现  直接进行转换
        if (k instanceof String){
            return (CACHE_PREFIX + k).getBytes();
        }
        // 如果不是,通过序列化工具间转化
        byte[] serialize = SerializationUtils.serialize(k);

        return serialize;
    }

    /**
     * 获取缓存的方法  根据k获取value
     * @param k
     * @return
     * @throws CacheException
     */
    public V get(K k) throws CacheException {
        System.out.println("mycache --- 从cache中获取缓存信息");
        byte[] value = jedisUtils.get(getByte(k));
        if (value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        /**
         * 这有一个更好的方式 使用二级缓存, 把信息存进二级缓存中,先从二级缓存中读取,
         * 二级缓存中如果没有 在从redis中读取,然后写入二级缓存中
         */
        return null;
    }

    /**
     * 存进缓存的方法
     * @param k
     * @param v
     * @return
     * @throws CacheException
     */
    public V put(K k, V v) throws CacheException {
        byte[] key = getByte(k);
        byte[] value = SerializationUtils.serialize(v);
        // 存进缓存中
        jedisUtils.set(key,value);
        // 设置保存时间
        jedisUtils.expire(key,600);
        return v;
    }

    /**
     * 删除缓存的方法
     * @param k
     * @return
     * @throws CacheException
     */
    public V remove(K k) throws CacheException {
        byte[] key = getByte(k);
        byte[] value = jedisUtils.get(key);
        jedisUtils.del(key);
        // 如果redis中有 这个key对应的value 返回这个
        if (value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        // 如果没有 返回为null
        return null;
    }

    /**
     * 清空缓存的方法
     * @throws CacheException
     */
    public void clear() throws CacheException {

    }

    public int size() {
        return 0;
    }

    public Set<K> keys() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }
}
