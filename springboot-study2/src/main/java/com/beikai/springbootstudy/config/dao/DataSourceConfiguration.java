package com.beikai.springbootstudy.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * @ClassName DataSourceConfiguration
 * @Description TODO  这是数据库连接测配置
 * @Author Admin
 * @Date 2018/11/21 23:40
 * @Version 1.0
 * @Configuration springboot中配置文件注解, 使用这个注解,在spring容器初始化的时候回加载
 * @mapper 设置dao层的扫描路径  设置这个路径后,容器会从这个路径下找到对应的dao层文件
 **/
@Configuration
@MapperScan("com.beikai.springbootstudy.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${jdbc.pool.max}")
    private int jdbcPoolMax;
    @Value("${jdbc.pool.min}")
    private int jdbcPoolMin;
    @Value("${jdbc.pool.autocommit}")
    private boolean jdbcPoolIsAutoCommit;
    @Value("${jdbc.pool.timeout}")
    private int jdbcPoolTimeout;
    @Value("${jdbc.pool.attempt}")
    private int jdbcPoolRetryAttempts;

    /**
     * 生成与spring-dao.xml对应的 bean datasource
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        // 生成datasource实例
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        // 设置连接数据库参数
        // 1. 设置连接驱动
        comboPooledDataSource.setDriverClass(jdbcDriver);
        // 2. 设置连接url
        comboPooledDataSource.setJdbcUrl(jdbcUrl);
        // 3. 设置连接用户名
        comboPooledDataSource.setUser(jdbcUsername);
        // 4. 设置连接密码
        comboPooledDataSource.setPassword(jdbcPassword);

        // 设置连接池私有参数
        // 1. 设置连接池最大线程数
        comboPooledDataSource.setMaxPoolSize(jdbcPoolMax);
        // 2. 设置连接池最小线程数
        comboPooledDataSource.setMinPoolSize(jdbcPoolMin);
        // 3. 关闭连接后不自动commit
        comboPooledDataSource.setAutoCommitOnClose(jdbcPoolIsAutoCommit);
        // 4. 设置连接池超时时间
        comboPooledDataSource.setCheckoutTimeout(jdbcPoolTimeout);
        // 5. 设置连接失败重试数
        comboPooledDataSource.setAcquireRetryAttempts(jdbcPoolRetryAttempts);

        return comboPooledDataSource;
    }
}
