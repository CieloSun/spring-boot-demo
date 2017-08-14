package com.example.demo;

import com.example.demo.Domain.Student;
import com.example.demo.Domain.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class StudentDomainTests {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void test() throws Exception {
        // 创建10条记录
        studentRepository.save(new Student("AAA", 10));
        studentRepository.save(new Student("BBB", 20));
        studentRepository.save(new Student("CCC", 30));
        studentRepository.save(new Student("DDD", 40));
        studentRepository.save(new Student("EEE", 50));
        studentRepository.save(new Student("FFF", 60));
        studentRepository.save(new Student("GGG", 70));
        studentRepository.save(new Student("HHH", 80));
        studentRepository.save(new Student("III", 90));
        studentRepository.save(new Student("JJJ", 100));
        // 测试findAll, 查询所有记录
        Assert.assertEquals(10, studentRepository.findAll().size());
        // 测试findByName, 查询姓名为FFF的User
        Assert.assertEquals(60, studentRepository.findByName("FFF").getAge().longValue());
        // 测试findUser, 查询姓名为FFF的User
        Assert.assertEquals(60, studentRepository.findUser("FFF").getAge().longValue());
        // 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
        Assert.assertEquals("FFF", studentRepository.findByNameAndAge("FFF", 60).getName());
        // 测试删除姓名为AAA的User
        studentRepository.delete(studentRepository.findByName("AAA"));
        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(9, studentRepository.findAll().size());
    }
}
