package com.beikai.springbootthread.service.impl;

import com.beikai.springbootthread.util.RedisUtil;
import com.beikai.springbootthread.mapper.UserDao;
import com.beikai.springbootthread.model.User;
import com.beikai.springbootthread.service.CreateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName RedisServiceImpl
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/20 23:04
 * @Version 1.0
 **/
@Service("RedisServiceImpl")
public class RedisServiceImpl implements CreateIdService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 产生订单号
     */
    @Override
    public void getId() {
        String key = "beikai:study:user:id";
        String prefix = getPrefix(new Date());

        long id = redisUtil.incr(key,1);

        User user = new User();
        user.setName(prefix + String.format("%1$05d",id));

        userDao.addUser(user);

        System.out.println("insert into threadId(name) VALUES('"
                + prefix + String.format("%1$05d",id)+"');");
    }

    /**
     * 通过时间戳生成前缀 - 可根据业务自己定义
     * @param date
     * @return
     */
    private String getPrefix(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        String dayFmt = String.format("%1$03d",day);
        System.out.println("dayfmt ---------> " + dayFmt);
        String hourFmt = String.format("%1$02d",hour);
        System.out.println("hourFmt ---------> " + hourFmt);

        return (year - 2000) + dayFmt + hourFmt;
    }
}
