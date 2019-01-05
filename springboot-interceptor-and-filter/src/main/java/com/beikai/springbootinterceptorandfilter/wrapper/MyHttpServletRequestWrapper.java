package com.beikai.springbootinterceptorandfilter.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * @ClassName MyHttpServletRequestWrapper
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/5 18:40
 * @Version 1.0
 *  重写 HttpServletRequestWrapper 用于获取 请求输入流
 **/
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     *     添加日志信息
      */
    private static Logger logger = LoggerFactory.getLogger(MyHttpServletRequestWrapper.class);

    private final byte[] body;

    private static final String CHARSETTYPE = "UTF-8";

    /**
     * 初始化 请求内容
     * @param request
     */
    public MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        logger.info("-----------------------------调用自定义 MyHttpServletRequestWrapper ---------------------------------------");
        // 获取请求内容
        String sessionStream = getBodySting(request);
        // 请求内容转换成 字节数组
        body = sessionStream.getBytes(Charset.forName(CHARSETTYPE));
    }

    /**
     * 获取请求体内容
     * @param request
     * @return
     */
    public String getBodySting(final ServletRequest request){

        //声明 字符串缓存
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = cloneInputStream(request.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream,Charset.forName(CHARSETTYPE)));
            String line = "";
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error("获取请求输入流出错");
            e.printStackTrace();
        }

        // 关闭 输入流 和 读取流
        finally {
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭输入流出错");
                    e.printStackTrace();
                }
            }
            if (null != reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("关闭读入流出错");
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 复制输入流
     * @param inputStream
     * @return
     */
    public InputStream cloneInputStream(ServletInputStream inputStream){

        // 创建字节流输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // 创建写入循环
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) > -1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            logger.error("cloneInputStream 中 写入出错");
            e.printStackTrace();
        }

        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    /**
     * 重写 getReader 方法
     * @return
     * @throws IOException
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 重写 getInputStream 方法
     * @return
     * @throws IOException
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        logger.info("-------------------------调用了重写的 getinputstream 方法---------------------------------------------");
        // 获取请求输入流
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }
}
