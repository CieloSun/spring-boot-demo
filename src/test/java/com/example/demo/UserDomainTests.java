package com.example.demo;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserDomainTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testStringRedis() throws Exception{
        stringRedisTemplate.opsForValue().set("Cielo","Good Boy");
        Assert.assertEquals("Good Boy",stringRedisTemplate.opsForValue().get("Cielo"));
    }
    @Test
    public void testObjectRedis() throws Exception{
        User user=new User();
        user.setId(1l);
        user.setName("Cielo");
        user.setAge(22);
        userRepository.save(user);
        Assert.assertEquals(user.getName(), userRepository.findOne(1l).getName());
    }
}
