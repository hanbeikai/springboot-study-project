package com.beikai.MutilOfDruid.scheduler;/**
 * Created with IntelliJ IDEA.
 * User: beikai
 * Date: 2020/4/30
 * Time: 18:20
 * Description:
 */

import cn.hutool.core.util.StrUtil;
import com.beikai.MutilOfDruid.dao.master.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author hbk
 * @ClassName:
 * @Description:
 * @date 2020/4/30 18:20
 */
@Component
@Configuration
@EnableScheduling
public class SchedulerDemo2 implements SchedulingConfigurer {

    @Autowired
    @SuppressWarnings("all")
    private UserDao cronMapper;

    /**
     * 功能描述:
     * 〈
     * create table cron(
     * id bigint not null PRIMARY key auto_increment comment '主键',
     * cron varchar(16) not null comment '定时',
     * task_num int(1) not null default 0 comment '定时任务号',
     * create_time TIMESTAMP not null default CURRENT_TIMESTAMP comment '创建时间'
     * ) comment '定时任务表';
     * <p>
     * insert into cron(cron,task_num) VALUES('0/6 * * * * ?',1);
     * <p>
     * 〉
     *
     * @param scheduledTaskRegistrar
     * @return : void
     * @author : hbk
     * @date : 2020/4/30
     */


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                // 添加任务内容
                () -> System.out.println("执行动态定时任务 : " + LocalDateTime.now().toLocalTime()),
                // 设置执行周期
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();

                    //2.2 合法性校验.
                    if (StrUtil.isNotBlank(cron)) {
                        // Omitted Code ..
                        //2.3 返回执行周期(Date)
                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }

                    return null;
                }
        );
    }
}
