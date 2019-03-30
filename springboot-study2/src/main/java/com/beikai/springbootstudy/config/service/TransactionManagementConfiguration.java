package com.beikai.springbootstudy.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @ClassName TransactionManagementConfiguration   配置service的事物管理
 * @Description TODO  对标spring-service里面的transactionManager 继承TransactionManagementConfiguration是因为开启annotation-driven
 * @Author Admin
 * @Date 2018/11/22 0:41
 * @Version 1.0
 *
 * @EnableTransactionManagement 首先使用注解@EnableTransactionManagement开启事务支持后 在service方法上添加@transaction 即可
 **/
@Configuration
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {

    /**
     * 注入datasourceconfiguration里面的datasource 通过createDataSource()方法获取
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 关于事务管理 需要返回platformTransactionManager 的实现
     * @return
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
