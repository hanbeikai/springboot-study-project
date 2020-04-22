package com.beikai;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@EnableSwagger2
public class DemoApplication {

    private final static Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info("---------------------------< 启动成功 >---------------------------");
    }

}
