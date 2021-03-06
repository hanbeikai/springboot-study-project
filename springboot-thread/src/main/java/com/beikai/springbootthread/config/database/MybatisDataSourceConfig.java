package com.beikai.springbootthread.config.database;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 标识当前 是一个配置文件 被spring容器 自动添加到容器中
 */
@Configuration
/**
 * @ClassName MybatisDataSourceConfig
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 22:00
 * @Version 1.0
 *  mybatis 配置文件
 **/
public class MybatisDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(MybatisDataSourceConfig.class);

    @Value("${mybatis.mapper-locations}")
    private String mapper_locations;

    /**
     * 自动从spring容器中获取datasource 注入到这个文件中
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 创建sqlsessionfactory 对象
     * @return
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        /**
         * 创建 sqlsessionfactoryBean 对象  并注入 datesource
         */
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        /**
         * 添加mapper映射文件所在路径
         */
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {

            sqlSessionFactoryBean.setMapperLocations(
                    resourcePatternResolver.getResources(mapper_locations));

            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

            if (null == sqlSessionFactory){
                logger.error("MybatisDataSourceConfig 中 获取sqlsessionfactory为空");
                return null;
            }

            sqlSessionFactory.getConfiguration().setCacheEnabled(Boolean.TRUE);
            return sqlSessionFactory;

        } catch (Exception e) {
            logger.error("MybatisDataSourceConfig 中 获取sqlsessionfactory出错 , 错误原因是 : " + e);
            throw new RuntimeException("获取sqlsessionfactory出错,错误原因是 : " + e);
        }
    }

    /**
     * 创建全局唯一的SqlSessionTemplate 来代理 sqlsessinfactory  (单例模式)  线程安全的
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
