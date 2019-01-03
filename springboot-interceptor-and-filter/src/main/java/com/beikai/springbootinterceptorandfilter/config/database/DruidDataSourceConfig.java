package com.beikai.springbootinterceptorandfilter.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 标识一下 这是个配置文件
 */
@Configuration
/**
 * 标识一下 开启事务
 */
@EnableTransactionManagement
/**
 * @ClassName DruidDataSourceConfi
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 20:51
 * @Version 1.0
 *  配置数据库连接池代码
 **/
public class DruidDataSourceConfig {
    /**
     * 添加日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    /**
     * 注入 数据库连接池参数封装类
     */
    @Autowired
    private DruidDataSourceSettings druidDataSourceSettings;

    public static String DRIVER_CLASSNAME;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 创建 DataSource 方法 并把参数传递进去
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource dataSource() throws SQLException {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(druidDataSourceSettings.getDriverClassName());
        druidDataSource.setUrl(druidDataSourceSettings.getUrl());
        DRIVER_CLASSNAME = druidDataSourceSettings.getDriverClassName();
        druidDataSource.setUsername(druidDataSourceSettings.getUsername());
        druidDataSource.setPassword(druidDataSourceSettings.getPassword());
        druidDataSource.setInitialSize(druidDataSourceSettings.getInitialSize());
        druidDataSource.setMinIdle(druidDataSourceSettings.getMinIdle());
        druidDataSource.setMaxActive(druidDataSourceSettings.getMaxActive());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceSettings.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceSettings.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidDataSourceSettings.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidDataSourceSettings.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidDataSourceSettings.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidDataSourceSettings.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidDataSourceSettings.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceSettings.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setFilters(druidDataSourceSettings.getFilters());
        druidDataSource.setConnectionProperties(druidDataSourceSettings.getConnectionProperties());
        druidDataSource.setUseGlobalDataSourceStat(druidDataSourceSettings.isUseGlobalDataSourceStat());

        logger.info("DruidDataSourceConfig 参数添加完成 : {}" + druidDataSource);

        return druidDataSource;
    }

    /**
     * 把datasource 注入到事务中
     * @return
     * @throws Exception
     */
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {

        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());

        logger.info("DruidDataSourceConfig 事务参数注入完成 : {}" + dataSourceTransactionManager);

        return dataSourceTransactionManager;
    }

}
