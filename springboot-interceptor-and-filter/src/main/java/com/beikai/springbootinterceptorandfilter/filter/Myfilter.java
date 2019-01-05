package com.beikai.springbootinterceptorandfilter.filter;

import com.beikai.springbootinterceptorandfilter.wrapper.MyHttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName Myfilter
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 19:18
 * @Version 1.0
 *  自定义 过滤器
 *
 *  @WebFilter (filterName = "Myfilter",urlPatterns = "/*")
 *      自动注入当前类到spring容器的 注解
 *
 *      filterName 拦截器类名
 *      urlPatterns 过滤路径
 **/
public class Myfilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(Myfilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("--------------------Myfilter过滤器 初始化----------------------------");
    }

    /**
     * 为了防止 请求流 读取一次就没有了 所以需要将流继续写出去
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("--------------------Myfilter过滤器 开始执行----------------------------");
        // 获取请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 把请求写入自定义的 requestwrapper中
        ServletRequest request = new MyHttpServletRequestWrapper(httpServletRequest);
        // 把重写后的请求重新写入请求连接中
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("--------------------Myfilter过滤器 销毁----------------------------");
    }
}
