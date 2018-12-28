package com.beikai.springboot_rabbitmq.task;

import com.alibaba.fastjson.JSON;
import com.beikai.springboot_rabbitmq.constant.Constants;
import com.beikai.springboot_rabbitmq.entity.BrokerMessageLog;
import com.beikai.springboot_rabbitmq.entity.OrderModel;
import com.beikai.springboot_rabbitmq.mapper.BrokerMessageLogMapper;
import com.beikai.springboot_rabbitmq.producer.OrderSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName RetryMessageTasker
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/28 0:15
 * @Version 1.0
 *  定时任务 实现
 **/
@Component
public class RetryMessageTasker {

    private static Logger logger = LoggerFactory.getLogger(RetryMessageTasker.class);

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    /**
     * 设置定时重新发送消息
     *  @Scheduled 控制方法定时执行
     *
     *  initialDelay  意思是延时指定时间执行第一次定时器 例如  设置 为十秒 fixedDelay 设置为15秒
     *      当定时器启动后 10秒 第一次执行定时器  以后每15秒执行一次定时器
     *  fixedDelay  定时器执行的时间间隔  从上次执行之后开始到下一次执行开始之前之间的时间间隔
     */
    @Scheduled(initialDelay = 5000,fixedDelay = 10000)
    public void reSend(){
        System.out.println("----------------------------定时任务开始--------------------------");
        // 获取 状态为 0 并且时间已经超时的 信息
        List<BrokerMessageLog> brokerMessageLogs = brokerMessageLogMapper.query4StatusAndTimeoutMessage();

        // 遍历查到的信息
        brokerMessageLogs.forEach(messageLog -> {
            if (messageLog.getTryCount() >= 3){
                // 当尝试次数超过三次的时候, 设置消息为 发送失败消息 等待人工进行处理
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE,new Date());

            }else {
                // 没有超过三次的时候  重新发送
                brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(),new Date());
                OrderModel orderModel = JSON.parseObject(messageLog.getMessage(),OrderModel.class);
                try {
                    orderSender.send(orderModel);
                } catch (Exception e) {
                    System.err.println("-----------------------异常处理-----------------------");
                    logger.error("重新发送消息失败 ,失败的原因是 : " + e);
                    throw new RuntimeException("重新发送消息失败 ,失败的原因是 : " + e);
                }
            }
        });
    }
}
