package com.beikai.databasetest.test;

import com.beikai.databasetest.Service.StudentService;
import com.beikai.databasetest.Service.impl.StudentServiceImpl;
import com.beikai.databasetest.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentService studentService;

    @Test
    public void insert() throws InterruptedException {
        long l = System.currentTimeMillis();
        for (int j = 0; j < 10; j++) {
            ExecutorService executorService = Executors.newFixedThreadPool(40);
            for (int i = 0; i < 7000; i++) {
                final int index = i;
                try {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            Student student = new Student();
                            student.setName("學生名 : " + Thread.currentThread().getName() + index);
                            studentService.insert(student);
                            System.out.println("当前线程是 : " + Thread.currentThread().getName());
                        }
                    });
                } catch (Exception e) {
                    logger.error("创建学生信息出错,错误原因是 : " + e);
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            System.out.println("創建結束了, 用时 ： " + (System.currentTimeMillis() - l ));
        }
    }

    @Test
    public void insertSelective() {
    }
}