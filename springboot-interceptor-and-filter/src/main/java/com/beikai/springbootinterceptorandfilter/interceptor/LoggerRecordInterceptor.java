package com.beikai.springbootinterceptorandfilter.interceptor;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName LoggerRecordInterceptor
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/3 23:17
 * @Version 1.0
 **/
@Configuration
public class LoggerRecordInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoggerRecordInterceptor.class);

    /**
     * 请求前调用(controller 方法调用前) 只有返回true才能继续向下执行 返回false 取消当前请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle : 请求前调用");

        // 将handle 强转 为 handlemethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取要调用的方法
        Method method = handlerMethod.getMethod();
        //获取方法上的access注解
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);

        if(null == annotation) {
            // 如果注解的值为空 说明不需要进行拦截, 直接放过
            return true;
        }

        if (null != annotation){
            // 不为空 获取里面的值
            String value = annotation.value();

            logger.info("LoggerRecordInterceptor 中 获取了 接口的注解的值 : " + value);

            return true;
        }
        return false;
    }

    /**
     * 请求后调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("postHandle : 请求后调用");
    }

    /**
     * 请求调用完成后回调方法,即在视图渲染完成后回调
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("afterCompletion : 请求调用完成后回调方法,即在视图渲染完成后回调");
    }
}
