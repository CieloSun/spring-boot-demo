package com.example.demo.Controller;

import com.example.demo.Domain.RedisUser;
import com.example.demo.Domain.RedisUserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("redisTest")     // 通过这里配置使下面的映射都在/users下，可去除
public class RedisUserController {
    private final RedisUserRepository redisUserRepository;

    @Autowired
    public RedisUserController(RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }

    @ApiOperation(value="获取用户列表")
    @GetMapping
    public List<RedisUser> getUserList() {
        return (List<RedisUser>) redisUserRepository.findAll();
    }
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "redisUser", value = "用户详细实体user", required = true, dataType = "RedisUser")
    @PostMapping
    public String postUser(@RequestBody RedisUser redisUser) {
        redisUserRepository.save(redisUser);
        return "success";
    }
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @GetMapping("{id}")
    public RedisUser getUser(@PathVariable Long id) {
        return redisUserRepository.findOne(id);
    }
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "redisUser", value = "用户详细实体user", required = true, dataType = "RedisUser")
    })
    @PutMapping("{id}")
    public String putUser(@PathVariable Long id, @RequestBody RedisUser redisUser) {
        RedisUser u = redisUserRepository.findOne(id);
        u.setName(redisUser.getName());
        u.setAge(redisUser.getAge());
        redisUserRepository.save(u);
        return "success";
    }
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        redisUserRepository.delete(id);
        return "success";
    }
    @ApiOperation(value = "清空全部用户")
    @DeleteMapping
    public String deleteUsers(){
        redisUserRepository.deleteAll();
        return "success";
    }
}