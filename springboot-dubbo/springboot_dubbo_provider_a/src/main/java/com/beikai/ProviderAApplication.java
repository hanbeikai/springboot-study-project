package com.beikai;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@MapperScan("com.beikai.dubbo.dao")  // 扫描dao层接口
public class ProviderAApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(ProviderAApplication.class);

	/**
	 * 生成一个线程
	 * @return
	 */
	@Bean
	public CountDownLatch countDownLatch(){
		return new CountDownLatch(1);
	}


	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new SpringApplicationBuilder(ProviderAApplication.class)
				.web(WebApplicationType.NONE)  // 非web 项目
				.run(args);

		//SpringApplication.run(ProviderBApplication.class, args);

		CountDownLatch bean = context.getBean(CountDownLatch.class);
		bean.await();
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("dubbo 生产者 a 启动了 ... ");
	}
}
