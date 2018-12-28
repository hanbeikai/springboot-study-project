package com.beikai.springboot_rabbitmq.config.database;


import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 标识 这是一个配置文件
 */
@Configuration
/**
 * 标识 这个配置文件需要在spring容器 加载MybatisDataSourceConfig 文件之后再加载
 */
@AutoConfigureAfter(MybatisDataSourceConfig.class)
/**
 * @ClassName MybatisMapperScanerConfig
 * @Description TODO
 * @Author Admin
 * @Date 2018/12/27 22:19
 * @Version 1.0
 *
 *  mybatis 扫描 mapper 文件 配置文件
 **/
public class MybatisMapperScanerConfig {

    /**
     * 创建 MapperScannerConfigurer 对象
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        // 创建 MapperScannerConfigurer 对象
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 从spring容器中找到 sqlSessionFactory 对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // 设置 mapper.java 文件路径
        mapperScannerConfigurer.setBasePackage("com.beikai.springboot_rabbitmq.mapper");

        // 返回
        return mapperScannerConfigurer;
    }
}
