package com.beikai.springbootinterceptorandfilter.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beikai.springbootinterceptorandfilter.model.APIResponseModel;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import com.beikai.springbootinterceptorandfilter.service.LoggerServicer;
import com.beikai.springbootinterceptorandfilter.util.DateUtils;
import com.beikai.springbootinterceptorandfilter.util.IPUtils;
import com.beikai.springbootinterceptorandfilter.wrapper.MyHttpServletRequestWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName MyLoggerAspect
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/10 20:32
 * @Version 1.0
 * <p>
 * 采用aop的方式 获取请求连接
 * @Aspect 定义当前类是一个 aspect 类
 * @Component 定义当前类到spring容器中
 * @Order(1) 定义当前类的启动级别
 *
 *  相对于拦截器 方式获取日志, aspect 方式获取日志更加简单,使用的代码更少,而且可以记录各个方法之间的调用, 比如所记录了 controller 层 , service 层 ,dao 层等,
 *      但是有个缺点是,如果方法没有到controller ,service,dao 等时,是不能触发切面记录的
 *
 *  使用拦截器的话,可以成请求层面上进行拦截,就算请求还没有触发controller ,比如说, 请求参数进行了校验设置,在加载请求参数的时候,就已经进行了异常处理,这时候也可以
 *      通过拦截器进行拦截,并保存,使用拦截器的缺点就是,在配置的时候,要在config中添加 设置的拦截器加载,为了方式请求参数被拦截器读取一次后无法被业务代码读取,
 *      需要重写 wrapper等,然后重写一个过滤器,并配置到config中,方便与在spring容器加载的时候添加过滤器
 **/
@Aspect
@Component
@Order(1)
public class MyLoggerAspect {

    private static Logger logger = LoggerFactory.getLogger(MyLoggerAspect.class);

    @Autowired
    private LoggerServicer loggerServicer;

    /**
     * 声明一个线程 用来保证切之前 与 切之后的同步问题
     */
    private static final ThreadLocal<RequestLoggerModel> REQUESTLOGGERMODELTHREADLOCAL = new ThreadLocal<RequestLoggerModel>();

    /**
     * 申明一个切点 里面是 execution表达式
     */
    @Pointcut("execution(public * com.beikai.springbootinterceptorandfilter.controller..*.*(..))")
    private void controllerAspect() {
    }

    /**
     * 声明一个切点, 里面是注解的值
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    private void annotationAspect() {
    }

    /**
     * 请求method前打印内容
     */
    @Before(value = "controllerAspect()&&annotationAspect()&&@annotation(annotation)")
    public void methodBefore(JoinPoint joinPoint, ApiOperation annotation) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        RequestLoggerModel requestLoggerModel = new RequestLoggerModel();

        try {
            // 获取请求日志记录的一些参数
            requestLoggerModel.setRequestTime(DateUtils.dateFormat(new Date(), DateUtils.HOUR_PATTERN));
            requestLoggerModel.setRequestIp(IPUtils.getIpAddress(request));
            requestLoggerModel.setRequestMethod(request.getRequestURI());

            // 获取请求参数
            String dataForm = "";
            JSONObject jsonObject = JSON.parseObject(new MyHttpServletRequestWrapper(request).getBodySting(request));
            dataForm = jsonObject.toJSONString();

            if (StringUtils.isNotBlank(dataForm)) {
                requestLoggerModel.setRequestParams(dataForm);
            }
            // 判断方法上是否有这个注解 如果没有 则不获取对应的值
            if (null != annotation) {
                requestLoggerModel.setMethodDescription(annotation.value());
            }

            requestLoggerModel.setRequestMessage("请求成功");

            try {
                loggerServicer.addRequestLogger(requestLoggerModel);
            } catch (Exception e) {
                logger.error("添加操作日志信息出错,错误原因是 : " + e);
            }

        } catch (ParseException e) {
            logger.error("获取请求参数异常,异常的原因是 : " + e);
            requestLoggerModel.setRequestMessage("获取请求参数异常,异常的原因是 : " + e);
        }

        REQUESTLOGGERMODELTHREADLOCAL.set(requestLoggerModel);
    }


    /**
     * 在方法执行完结后打印返回内容
     */
    @AfterReturning(returning = "o", pointcut = "controllerAspect()&&annotationAspect()&&@annotation(annotation)")
    public void methodAfterReturing(Object o,ApiOperation annotation) {

        // 从线程中获取 之前保存的对象 用来绑定响应参数
        RequestLoggerModel requestLoggerModel = REQUESTLOGGERMODELTHREADLOCAL.get();

        try {
            APIResponseModel apiResponseModel = (APIResponseModel) o;

            // 获取响应参数 等信息
            requestLoggerModel.setResponseCode(apiResponseModel.getCode());
            requestLoggerModel.setResponseMessage(apiResponseModel.getMessage());
            if (null == apiResponseModel.getData()) {
                requestLoggerModel.setResponseParams("null");
            } else {
                requestLoggerModel.setResponseParams(JSON.toJSONString(apiResponseModel.getData()));
            }

            requestLoggerModel.setResponseTime(DateUtils.dateFormat(new Date(), DateUtils.HOUR_PATTERN));

            try {
                loggerServicer.updateRequestLogger(requestLoggerModel);
            } catch (Exception e) {
                logger.error("修改操作日志信息出错,错误原因是 : " + e);
            }

        } catch (ParseException e) {
            logger.error("MyLoggerAspect.methodAfterReturing 获取响应参数出错---> " + e);
        } finally {
            REQUESTLOGGERMODELTHREADLOCAL.remove();
        }
    }

    /**
     * 如果响应参数发生了异常,则会调用这个方法
     *
     * @param e
     */
    @AfterThrowing(throwing = "e", pointcut = "controllerAspect()&&annotationAspect()&&@annotation(annotation)")
    public void methodAfterReturingThrowe(Throwable e,ApiOperation annotation) {
        // 从线程中获取 之前保存的对象 用来绑定响应参数
        RequestLoggerModel requestLoggerModel = REQUESTLOGGERMODELTHREADLOCAL.get();

        try {

            String s = JSONObject.toJSONString(e);

            // 获取响应参数 等信息
            requestLoggerModel.setResponseCode("请求异常");
            requestLoggerModel.setResponseMessage(e.getMessage());

            requestLoggerModel.setResponseParams(JSONObject.toJSONString(e));

            requestLoggerModel.setResponseTime(DateUtils.dateFormat(new Date(), DateUtils.HOUR_PATTERN));

            try {
                loggerServicer.updateRequestLogger(requestLoggerModel);
            } catch (Exception el) {
                logger.error("修改操作日志信息出错,错误原因是 : " + e);
            }

        } catch (ParseException el) {
            logger.error("MyLoggerAspect.methodAfterReturing 获取响应参数出错---> " + e);
        } finally {
            REQUESTLOGGERMODELTHREADLOCAL.remove();
        }
    }
}
