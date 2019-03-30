package com.beikai.springbootstudy.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @ClassName SessionFactoryConfiguration
 * @Description TODO  这是配置mybatis的配置
 * @Author Admin
 * @Date 2018/11/22 0:15
 * @Version 1.0
 *  @Configuration springboot中配置文件注解, 使用这个注解,在spring容器初始化的时候回加载
 *
 **/
@Configuration
public class SessionFactoryConfiguration {
    /**
     * 设置mybatis-config.xml的配置文件路径
     */

    private static String mybatisConfigPath;
    @Value("${mybatis_config_path}")
    public void setMybatisConfigPath(String mybatisConfigPath){
        SessionFactoryConfiguration.mybatisConfigPath = mybatisConfigPath;
    }


    /**
     * 设置 mapper的扫描路径
     */

    private static String mapperpath;
    @Value("${mapper_path}")
    public void setMapperpath(String mapperpath) {
        SessionFactoryConfiguration.mapperpath = mapperpath;
    }

    /**
     * 设置 typeAlias 包扫描路径 (实体类)
     */

    private static String typeAlieasPackage;
    @Value("${type_Alias_package}")
    public void setTypeAlieasPackage(String typeAlieasPackage) {
        SessionFactoryConfiguration.typeAlieasPackage = typeAlieasPackage;
    }

    @Autowired
    private DataSource dataSource;

    /**
     * 创建sqlsessionfactorybean实例 并设置 configtion 设置mapper映射路径 设置datasource数据源
     * @return
     * @throws IOException
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 1. 设置mybatis-config.xml文件的扫描路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigPath));
        // 2. 设置mapper文件的扫描路径
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperpath;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        // 3. 设置DataSource
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 4. 设置typeAlias 包扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAlieasPackage);

        return sqlSessionFactoryBean;
    }

}
