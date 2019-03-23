package com.beikai.databasetest.test;

import com.beikai.databasetest.Service.CourseService;
import com.beikai.databasetest.Service.StudentService;
import com.beikai.databasetest.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Test
    public void insert() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Course course = new Course();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    course.setName("课程名 : " + Thread.currentThread().getName() + index);
                    int insert = courseService.insert(course);
                    System.out.println("  " +insert+ "  当前线程是 : " + Thread.currentThread().getName());
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("創建結束了");

    }

    @Test
    public void insertSelective() {


    }
}