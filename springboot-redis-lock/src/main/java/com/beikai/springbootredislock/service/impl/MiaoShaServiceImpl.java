package com.beikai.springbootredislock.service.impl;

import com.beikai.springbootredislock.mapper.MiaoShaMapper;
import com.beikai.springbootredislock.service.MiaoShaService;
import com.beikai.springbootredislock.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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


    @Autowired
    private RedisUtil redisUtil;



    /**
     * 秒杀的方法
     * @param goodsCode
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object miaosha(String goodsCode, final String userId) {
        System.out.println("-----------------------------------");
        boolean result = false;
        try {
            try {
                int num = miaoShaMapper.buy(goodsCode,userId);
                if (num == 1){
                    // 秒杀失败
                    result = true;
                }

                System.out.println("秒杀结果是 : " + result);
            } catch (Exception e) {
                log.error("秒杀时对数据库中的操作失败 : " + e);
                e.printStackTrace();
            }

            if (result){
                // 更新库存
                // 实例 如果秒杀成功,更新一次库存(库存的值一般在缓存中,供页面快速查询)
                Integer count = miaoShaMapper.getCount(goodsCode);
                // 模拟线程运行延时,随机睡眠
                if (null != count && count % 2 == 1){
                    Thread.sleep(new Random().nextInt(500));
                }

                // 存进缓存中
                stringRedisTemplate.opsForValue().set(goodsCode,count+"");
                redisUtil.set(goodsCode,count);

            }


        } catch (Exception e) {
            log.error("秒杀失败 : " + e);
            e.printStackTrace();
        }

        return result;
    }
}
