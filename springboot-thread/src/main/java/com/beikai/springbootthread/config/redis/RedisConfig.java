package com.beikai.springbootthread.config.redis;

import com.beikai.springbootthread.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author Admin
 * @Date 2019/1/20 21:28
 * @Version 1.0
 *  redis 配置类
 **/
@Configuration
@PropertySource("classpath:config/redis.properties")
public class RedisConfig {

    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;


/*    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer mmaxRedirectsac;*/

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private int timeout;

    /**
     * 配置 jedispoolconfig 连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedispoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);

        return jedisPoolConfig;
    }

    /**
     * 单机版配置连接工厂
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        // 添加连接池
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //IP地址
        jedisConnectionFactory.setHostName(hostName);
        //端口号
        jedisConnectionFactory.setPort(port);
        // 如果redis有密码
        jedisConnectionFactory.setPassword(password);
        // 客户端超时时间单位是毫秒
        jedisConnectionFactory.setTimeout(timeout);

        return jedisConnectionFactory;
    }

    /**
     * 实例化redistemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();

        initRedisTemplate(redisTemplate,redisConnectionFactory);

        return redisTemplate;

    }

    /**
     * 设置数据存入redis 的序列化方式 并开启事务
     * @param redisTemplate
     * @param redisConnectionFactory
     */
    private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate,
                                   RedisConnectionFactory redisConnectionFactory) {
        // 如果不配置serialize 那么存储的时候缺省使用string,如果使用user类型存储,会提示错误 user can`t cast to string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }

    /**
     * 注入封装redisTemplate
     * @param redisTemplate
     * @return
     */
    @Bean(name = "restUtil")
    public RedisUtil redisUtil(RedisTemplate<String,Object> redisTemplate){
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}
