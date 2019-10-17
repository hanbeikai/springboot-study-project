package com.beikai.springbootmqkafkaproduct;

import com.beikai.springbootmqkafkaproduct.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringbootMqKafkaProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMqKafkaProductApplication.class, args);
	}

	@Autowired
	private Product product;

	@PostConstruct
	public void init() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			// 调用kafka 发送消息
			Thread.sleep(1000);
			product.sendLog(Long.parseLong(i+""));
			
		}
	}

}
