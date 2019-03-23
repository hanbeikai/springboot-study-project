package com.beikai.RedisLock.service.impl;

import com.beikai.RedisLock.mapper.MiaoShaMapper;
import com.beikai.RedisLock.service.MiaoShaService;
import com.beikai.RedisLock.util.MyRedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName MiaoShaServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/21 21:52
 * @Version 1.0
 **/
@Service
@Slf4j
public class MiaoShaServiceImpl implements MiaoShaService {


    @Autowired
    private MiaoShaMapper miaoShaMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 声明一个锁
     */
    // Lock lock = new ReentrantLock();

    /**
     * 使用自定义的锁
     */
    Lock lock = null;

    /**
     *  初始化 当spring初始化好后 会触发这个方法  这时 StringRedisTemplate 已经初始化完成了
     */
    @PostConstruct
    public void init(){
        lock = new MyRedisLock(stringRedisTemplate,"goods_codes_lock_bike",10);
    }


   /* @Autowired
    private RedisUtil redisUtil;*/


    /**
     * 秒杀的方法
     *
     * @param goodsCode
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object miaosha(String goodsCode, final String userId) {
        // 给方法加锁  lock 的加锁有很多中方式
        lock.lock();
        boolean result = false;
        try {

            int num = miaoShaMapper.buy(goodsCode, userId);
            if (num == 1) {
                // 秒杀失败
                result = true;
            }

            System.out.println("秒杀结果是 : " + result);

            if (result) {
                // 更新库存
                // 实例 如果秒杀成功,更新一次库存(库存的值一般在缓存中,供页面快速查询)
                Integer count = miaoShaMapper.getCount(goodsCode);
                // 模拟线程运行延时,随机睡眠
                if (null != count && count % 2 == 1) {
                    Thread.sleep(new Random().nextInt(500));
                }

                // 存进缓存中
                stringRedisTemplate.opsForValue().set(goodsCode, count + "");
                //redisUtil.set(goodsCode,count);
            }

        } catch (Exception e) {
            log.error("秒杀失败 : " + e);
            e.printStackTrace();
        }finally {
            // 在finally中 释放锁
            lock.unlock();
        }

        return result;
    }
}
