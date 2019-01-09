package com.beikai.springbootinterceptorandfilter.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beikai.springbootinterceptorandfilter.model.RequestLoggerModel;
import com.beikai.springbootinterceptorandfilter.service.LoggerServicer;
import com.beikai.springbootinterceptorandfilter.util.DateUtils;
import com.beikai.springbootinterceptorandfilter.util.IPUtils;
import com.beikai.springbootinterceptorandfilter.wrapper.MyHttpServletRequestWrapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName LoggerRecordInterceptor
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/3 23:17
 * @Version 1.0
 **/
@Component
public class LoggerRecordInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoggerRecordInterceptor.class);

    @Autowired
    private LoggerServicer loggerServicer;

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
        logger.info("----------------------------- preHandle : 请求前调用 -----------------------------");

        RequestLoggerModel requestLoggerModel = new RequestLoggerModel();

        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        // 将handle 强转 为 handlemethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取要调用的方法
        Method method = handlerMethod.getMethod();
        //获取方法上的access注解
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);

        if (null != annotation){
            // 不为空 获取里面的值
            String value = annotation.value();
            requestLoggerModel.setMethodDescription(value);
            logger.info("LoggerRecordInterceptor 中 获取了 接口的注解的值 : " + value);
        }
        try {
            // 设置相应 编码格式
            response.setHeader("Content-type","application/json;charset-UTF-8");

            String requestURI = request.getRequestURI();

            requestLoggerModel.setRequestIp(IPUtils.getIpAddress(request));
            requestLoggerModel.setRequestMethod(requestURI);

            String dataForm = "";
            JSONObject jsonObject = JSON.parseObject(new MyHttpServletRequestWrapper(request).getBodySting(request));
            dataForm = jsonObject.toJSONString();

            requestLoggerModel.setRequestParams(dataForm);

            requestLoggerModel.setRequestTime(DateUtils.dateFormat(new Date(),DateUtils.HOUR_PATTERN));

            requestLoggerModel.setRequestMessage("请求成功");

            /*try {
                loggerServicer.addRequestLogger(requestLoggerModel);
            } catch (Exception e) {
                logger.error("添加请求日志信息出错 ---> " + e);
                requestLoggerModel.setRequestMessage("添加请求日志信息出错 ---> " + e);
                return true;
            }*/

        } catch (Exception e) {
            logger.error("获取请求内容出错 ---> " + e);
            requestLoggerModel.setRequestMessage("获取请求内容出错 ---> " + e);
            request.setAttribute("LoggerRecordInterceptor_model",requestLoggerModel);
            return true;
        }

        request.setAttribute("LoggerRecordInterceptor_model",requestLoggerModel);
        return true;
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
        logger.info("----------------------------- postHandle : 请求后调用 -----------------------------");
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
        logger.info("----------------------------- afterCompletion : 请求调用完成后回调方法,即在视图渲染完成后回调 -----------------------------");
    }
}
