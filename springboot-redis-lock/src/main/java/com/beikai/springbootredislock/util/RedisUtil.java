package com.beikai.springbootredislock.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/20 19:33
 * @Version 1.0
 *  redis 工具类
 **/

public class RedisUtil {

    /**
     * 声明 redisTemplate
     */

    private RedisTemplate<String,Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // -----------------------------------通过类方法------------------------------------------

    /**
     * 设置缓存失效时间
     * @param key  key
     * @param time 失效时间  单位 秒
     * @return  成功 / 失败
     */
    public boolean expire(String key,long time){
        try {

            if (time > 0) {
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更加key 获取过期时间
     * @param key  key
     * @return  失效时间
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 根据key 判断key是否存在
     * @param key  key
     * @return  是 / 否
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除指定key的缓存
     * @param key key
     * @return 成功 / 失败
     */
    public boolean delete(String ... key){

        try {
            if (null != key && key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =============================string 方法===========================================

    /**
     * 普通缓存获取
     * @param key  key键
     * @return value
     */
    public Object get(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key  key
     * @param value  值
     * @return  成功 / 失败
     */
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入 并设置过期时间
     * @param key key
     * @param value  value
     * @param time  过期时间 如果大于0 设置过期时间 如果不大于零 设置为无期限  单位 秒
     * @return  成功 / 失败
     */
    public boolean set(String key,Object value,long time){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else {
                redisTemplate.opsForValue().set(key,value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置递增
     * @param key key
     * @param delta  递增因子  (增加的值)
     * @return
     */
    public long incr(String key,long delta){
        if (delta < 0){
            throw new RuntimeException("递增因子必须大于零");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 设置递减
     * @param key key
     * @param delta  递增因子  (增减的值)
     * @return
     */
    public long decr(String key,long delta){
        if (delta < 0){
            throw new RuntimeException("递减因子必须大于零");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    // ======================================Map 方法=======================================================

    /**
     * 通过 key  获取hash中的 值
     * @param key key (不能为null)
     * @param item item map中的key  (不能为null)
     * @return  map 中的value
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取key 对应的 map (所有键值对)
     * @param key key
     * @return map  对应的多个键值对
     */
    public Map<Object,Object> hgetAll(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 缓存 设置 map
     * @param key key
     * @param map map
     * @return 成功 / 失败
     */
    public boolean hsetAll(String key,Map<Object,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存 设置 map  并设置缓存时间
     * @param key key
     * @param map map
     * @param time 缓存失效时间 单位: 秒  如果time 不大于0 设置时间无期限
     * @return 成功 / 失败
     */
    public boolean hsetAll(String key,Map<Object,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if (time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hashset表中添加数据,如果不存在,将创建
     * @param key key
     * @param item map 中的 key
     * @param value value
     * @return 成功 / 失败
     */
    public boolean hset(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hashset表中添加数据,如果不存在,将创建
     * @param key key
     * @param item map 中的 key
     * @param value value
     * @param time 时间(秒),如果已经存在的hash表有过期时间,将会替换原有的时间
     * @return 成功 / 失败
     */
    public boolean hset(String key,String item,Object value,long time){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if (time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key key
     * @param item map 中的 key, 可以是多个,不可以为null
     * @return 成功 / 失败
     */
    public boolean hdel(String key,Object ... item){
        try {
            redisTemplate.opsForHash().delete(key,item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断hash中是否有指定key下指定 item的值
     * @param key key 不能为null
     * @param item 项 不能为null
     * @return 有 / 无
     */
    public boolean hHasKye(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /**
     * hash 递增, 如果不存在,就会创建一个,并把新增后的值返回
     * @param key key
     * @param item 项
     * @param delta 要加的值(大于0)
     * @return 增加后的值
     */
    public double hincr(String key,String item,double delta){
        return redisTemplate.opsForHash().increment(key,item,delta);
    }

    /**
     * hash 递减, 如果不存在,就会创建一个,并把新减后的值返回
     * @param key key
     * @param item 项
     * @param delta 要减的值(大于0)
     * @return 减少后的值
     */
    public double hdecr(String key,String item,double delta){
        return redisTemplate.opsForHash().increment(key,item,-delta);
    }

    // ===========================Set 方法================================================

    /**
     * 根据key 获取set中的所有值
     * @param key key
     * @return 缓存中的set 如果不存在 返回为null
     */
    public Set<Object> sget(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断指定的key 和 value 是否在set中存在
     * @param key key
     * @param value 值
     * @return 存在 / 不存在
     */
    public boolean sHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放进set缓存
     * @param key key
     * @param value 值
     * @return 成功的个数
     */
    public long sset(String key,Object ... value){
        try {
            return redisTemplate.opsForSet().add(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将数据放进set中,并设置过期时间
     * @param key key
     * @param time 过期时间 (秒)
     * @param value 值
     * @return 成功的个数
     */
    public long sset(String key,long time,Object ... value){
        try {
            long count = redisTemplate.opsForSet().add(key,value);
            if (time > 0){
                expire(key,time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取 set 缓存的长度
     * @param key key
     * @return set 缓存的长度,如果没有,返回为0
     */
    public long sgetSetSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除指定key的value
     * @param key key
     * @param value 值
     * @return 移除成功的个数
     */
    public long sdelete(String key,Object ... value){
        try {
            return redisTemplate.opsForSet().remove(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //===================================List 方法==============================================

    /**
     * 获取list缓存的内容
     * @param key key
     * @param start  范围的开始
     * @param end  范围的结束  0 到 -1 代表所有值
     * @return list的内容
     */
    public List<Object> lGet(String key,long start,long end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key key
     * @return 长度
     */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key key键
     * @param index index索引,index >= 0,0表示表头,以此类推,index < 0时,-1 表示表尾
     * @return 获取的list中的值
     */
    public Object lGetIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key,index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 放入缓存
     * @param key key
     * @param value 值
     * @return 成功 / 失败
     */
    public boolean lSet(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入缓存,并设置缓存存在时间
     * @param key key
     * @param value 值
     * @param time 缓存有效时间
     * @return 成功 / 失败
     */
    public boolean lSet(String key,Object value,long time){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            if (time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key key
     * @param value value
     * @return 成功 / 失败
     */
    public boolean lSet(String key,List<Object> value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存,并设置缓存存在时间
     * @param key key
     * @param value 值
     * @param time 缓存有效时间
     * @return 成功 / 失败
     */
    public boolean lSet(String key,List<Object> value,long time){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            if (time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将多个 数据 放入缓存
     * @param key key
     * @param value value
     * @return 成功 / 失败
     */
    public boolean lSet(String key,Object ... value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将多个 数据 放入缓存,并设置缓存存在时间
     * @param key key
     * @param value 值
     * @param time 缓存有效时间
     * @return 成功 / 失败
     */
    public boolean lSet(String key,long time,Object ... value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            if (time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key key
     * @param index 索引
     * @param value 新的值
     * @return 成功 / 失败
     */
    public boolean lUpdateIndex(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key,index,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除 N 个值为 value
     * @param key key
     * @param count 移除的数量
     * @param value 要移除的值
     * @return 移除成功的数量
     */
    public long lRemove(String key,long count,Object value){
        try {
            long num = redisTemplate.opsForList().remove(key,count,value);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
