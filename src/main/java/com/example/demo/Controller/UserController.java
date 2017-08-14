package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value="获取用户列表")
    @GetMapping
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping
    public String postUser(@RequestBody User user) {
        userRepository.save(user);
        return "success";
    }
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findOne(id);
    }
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PutMapping("{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = userRepository.findOne(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        userRepository.save(u);
        return "success";
    }
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.delete(id);
        return "success";
    }
    @ApiOperation(value = "清空全部用户")
    @DeleteMapping
    public String deleteUsers(){
        userRepository.deleteAll();
        return "success";
    }
}