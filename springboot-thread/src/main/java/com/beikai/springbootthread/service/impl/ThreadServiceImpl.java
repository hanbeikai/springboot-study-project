package com.beikai.springbootthread.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beikai.springbootthread.mapper.UserDao;
import com.beikai.springbootthread.model.User;
import com.beikai.springbootthread.service.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName ThreadServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/6 23:06
 * @Version 1.0
 **/
@Service
public class ThreadServiceImpl implements IThreadService {

    @Autowired
    private UserDao userDao;

    /**
     * 使用runnable 的方式 获取用户信息
     *
     * @return
     */
    @Override
    public Object getUserInfoByRunnable() {

        long starttime = System.currentTimeMillis();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setName("赵子龙");
                user.setAge("20");

                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(user));

                System.out.println("getUserInfoByRunnable 方法中的线程名是 : "
                        + Thread.currentThread().getName() + "   使用的时间是 : " + (System.currentTimeMillis() - starttime)
                        + "   获取的内容是 : ---> " + jsonObject.toJSONString());
            }
        }).start();

        return System.currentTimeMillis() - starttime;
    }

    /**
     * 使用 callable 的方式 获取用户信息
     *
     * @return
     */
    @Override
    public Object getUserInfoByCallable() throws ExecutionException, InterruptedException {

        long starttime = System.currentTimeMillis();

        // 1 . 使用 callable 封装业务逻辑
        Callable<JSON> getUserName = new Callable<JSON>() {
            @Override
            public JSON call() throws Exception {

                User user = new User();
                user.setName("关羽");

                // 设置 线程睡眠 2秒
                //Thread.sleep(2000L);

                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(user));

                System.out.println("getUserName 方法中的线程名是 : "
                        + Thread.currentThread().getName() + "   使用的时间是 : " + (System.currentTimeMillis() - starttime)
                        + "   获取的内容是 : ---> " + jsonObject.toJSONString());

                return jsonObject;
            }
        };

        long startTime2 = System.currentTimeMillis();

        // 使用 callable 封装业务逻辑
        Callable<JSON> getUserAge = new Callable<JSON>() {
            @Override
            public JSON call() throws Exception {

                User user = new User();
                user.setAge("20");

                // 设置 线程睡眠 2秒
                //Thread.sleep(3000L);

                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(user));

                System.out.println("getUserAge 方法中的线程名是 : "
                        + Thread.currentThread().getName() + "   使用的时间是 : " + (System.currentTimeMillis() - startTime2)
                        + "   获取的内容是 : ---> " + jsonObject.toJSONString());

                return jsonObject;
            }
        };

        //2 . callable 绑定 线程
        FutureTask<JSON> getUserNmeCallable = new FutureTask<>(getUserName);
        FutureTask<JSON> getUserAgeCallable = new FutureTask<>(getUserAge);

        /**
         * futureTaks 特征 特点
         *  1 . 泛型定义
         *  2. 构造函数 要有一个callable
         *  3. futuretask 应该实现了runnable 实现了run方法
         *  4. 有一个get方法可以返回callable的执行结果 此方法还具有阻塞的效果
          */

        // 3 . 封装线程中
        new Thread(getUserNmeCallable).start();
        new Thread(getUserAgeCallable).start();

        // 4 . 获取线程中的数据  合并成一个数据
        JSONObject json = new JSONObject();
        // 5 . 在合并的时候 如果有task 任务没有被线程执行完毕 此处会有等待的效果
        json.putAll((Map<? extends String, ? extends Object>) getUserNmeCallable.get());
        json.putAll((Map<? extends String, ? extends Object>) getUserAgeCallable.get());

        System.out.println("整合的时候方法中的线程名是 : "
                + Thread.currentThread().getName() + "   使用的时间是 : " + (System.currentTimeMillis() - starttime)
                + "   获取的内容是 : ---> " + json.toJSONString());

        return json;
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public List<User> getUser() {

        List<User> users = userDao.getUser();

        return users;
    }
}
