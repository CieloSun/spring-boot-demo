package com.example.demo;

import com.example.demo.Tasks.AsyncTasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AsyncTests {
    @Autowired
    private AsyncTasks asyncTasks;

    @Test
    public void test() throws Exception{
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncTasks.doTaskOne();
        Future<String> task2 = asyncTasks.doTaskTwo();
        Future<String> task3 = asyncTasks.doTaskThree();
        while(!(task1.isDone() && task2.isDone() && task3.isDone())) {
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
