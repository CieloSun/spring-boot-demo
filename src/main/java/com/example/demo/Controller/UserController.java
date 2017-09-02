package com.example.demo.Controller;

import com.example.demo.Domain.AuthInfo;
import com.example.demo.Domain.User;
import com.example.demo.Service.TokenService;
import com.example.demo.Service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation("用户登录")
    @PostMapping("token")
    public String login(@RequestBody AuthInfo authInfo) throws Exception {
        String email=authInfo.getEmail();
        String password=authInfo.getPassword();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) throw new Exception("用户名和密码不能为空值!");
        User user = userService.findByEmailAndPassword(email, password);
        if (user == null) throw new Exception("用户名或密码错误!");
        return tokenService.save(user.getId());
    }

    @ApiOperation("获取用户可见信息")
    @GetMapping("{token}")
    public User showUser(@PathVariable String token) throws Exception{
        Long userId=tokenService.get(token);
        if (userId == null) {
            throw new Exception("验证口令无效");
        }
        User user=userService.findById(userId);
        user.setId(null);
        user.setPassword(null);
        return user;
    }

}
