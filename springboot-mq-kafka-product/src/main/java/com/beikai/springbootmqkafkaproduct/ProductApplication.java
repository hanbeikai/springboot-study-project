package com.beikai.springbootmqkafkaproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	/*@Autowired
	private Product product;*/

	/*@PostConstruct
	public void init() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			// 调用kafka 发送消息
			Thread.sleep(1000);
			product.sendLog(Long.parseLong(i+""));
			
		}
	}*/

	/*@Autowired
	private CanalSimple canalSimple;

	@PostConstruct
	public void init(){
		canalSimple.syn();
	}*/

}
