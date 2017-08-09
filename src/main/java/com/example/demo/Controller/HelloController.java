package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class HelloController {
    @GetMapping("hello")
    @ResponseBody
    public String index(){
        return "Hello World";
    }
    @GetMapping
    public String page(ModelMap modelMap){
        modelMap.addAttribute("title","Cielo's Home");
        return "index";
    }
    @GetMapping("errorTest")
    @ResponseBody
    public String errorTest() throws Exception{
        throw new Exception("发生错误！");
    }
}
