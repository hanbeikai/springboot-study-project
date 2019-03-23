package com.beikai.RedisLock.util;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyRedisLock
 * @Description TODO
 * @Author Admin
 * @Date 2019/3/23 15:33
 * @Version 1.0
 * <p>
 * 自定义 redis 分布式锁
 **/
public class MyRedisLock implements Lock {

    private StringRedisTemplate redisUtil;

    private String resourcesName;

    private int timeout;

    /**
     * 构建一把锁
     *
     * @param redisUtil
     * @param resourcesName 资源唯一标识符
     * @param timeout       有效期 单位 秒
     */
    public MyRedisLock(StringRedisTemplate redisUtil, String resourcesName, int timeout) {
        this.redisUtil = redisUtil;
        this.resourcesName = "lock_" + resourcesName;
        this.timeout = timeout;
    }

    /**
     * 通过 jvm 的锁的使用,保证一个jvm中只有一个可以获取锁
     */
    Lock lock = new ReentrantLock();

    /**
     * 加锁  一直等到有锁为止
     *
     * 目前这种方式存在也还是存在一种问题,
     *  当第一个jvm 请求处理的时间超过了锁的有效时间,redis锁会被释放,另一个请求就会到,此时就会出现死锁现象,
     *  怎么避免呢?
     *      目前这种情况很难避免,因为使用分布式,就要考虑到服务器挂掉的可能,所以锁必须设置超时时间,但是超时时间又可能存在上述情况
     *      所以,临时的办法是 超时时间设置长一点, 代码的执行时间监控做起来
     *
     *
     *  redlock算法
     *
     *
     */
    @Override
    public void lock() {
        lock.lock();

        // 通过循环,反复尝试获取锁
        /*while (!tryLock()){
            // 没有获取锁的情况,线程睡眠 0.1 秒
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        try {
            // 订阅指定redis主体,用于接受释放锁的信号
            redisUtil.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    try {
                        // java j.u.c  设置
                        CountDownLatch countDownLatch = new CountDownLatch(1);
                        // subscribe 立刻会返回结果  是否监听成功,是否订阅完毕  subscribe
                        redisConnection.subscribe((message, pattern) -> {
                            // 收到通知 立即去抢锁
                            // 计数器
                            countDownLatch.countDown();

                        }, ("release_lock_" + resourcesName).getBytes());
                        // 等到有通知的时候返回  (timeout,TimeUnit.SECONDS) 设置超时时间
                        countDownLatch.await(timeout, TimeUnit.SECONDS);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 0L;
                }
            });
        } finally {
            lock.unlock();
        }

    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        // 删除 redis中的锁
        redisUtil.delete(this.resourcesName);

        // 发送一个通过给等待的请求  通过redis 发布订阅机制
        // 监听命令是 subscriber 关键字
        // 发布命令是 public 关键字
        redisUtil.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Long receive = redisConnection.publish(("release_lock_" + resourcesName).getBytes(), "``".getBytes());
                return receive;
            }
        });

    }

    /**
     * 尝试获取锁
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        // set命令, 在redis中存放锁的标记
        Boolean lockresult = redisUtil.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                String value = "";
                /**
                 * resourcesName.getBytes() 资源唯一标识符
                 * Expiration.seconds(timeout)  有效期 单位秒
                 * RedisStringCommands.SetOption.SET_IF_ABSENT  redis 的命令  SET_IF_ABSENT  是否存在
                 */
                Boolean result = redisConnection.set(resourcesName.getBytes(), value.getBytes(),
                        Expiration.seconds(timeout), RedisStringCommands.SetOption.SET_IF_ABSENT);
                return result;
            }
        });
        return lockresult;
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
