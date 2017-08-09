package com.example.demo.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {
    @GetMapping("hello")
    public String index(){
        return "Hello World";
    }
    @GetMapping
    public String page(ModelMap modelMap){
        modelMap.addAttribute("title","Cielo's Home");
        return "index";
    }
}
