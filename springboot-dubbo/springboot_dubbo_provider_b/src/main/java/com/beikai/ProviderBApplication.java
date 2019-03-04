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


/**
 * 因为 provider 不是web项目,所以启动方式需要调整,并且为了防止它启动main后,因为不是web方式启动,直接退出,所以需要添加一个阻塞
 */
@SpringBootApplication
@MapperScan("com.beikai.dubbo.dao")  // 扫描dao层接口
public class ProviderBApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(ProviderBApplication.class);

	/**
	 * 生成一个线程
	 * @return
	 */
	@Bean
	public CountDownLatch countDownLatch(){
		return new CountDownLatch(1);
	}

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new SpringApplicationBuilder(ProviderBApplication.class)
				.web(WebApplicationType.NONE)  // 非web 项目
				.run(args);

		//SpringApplication.run(ProviderBApplication.class, args);

		CountDownLatch bean = context.getBean(CountDownLatch.class);
		bean.await();
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("dubbo  b 启动了 ... ");

	}
}
