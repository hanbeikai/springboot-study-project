package com.beikai.springboottestdemo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestDemoApplicationTests {



    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

}
