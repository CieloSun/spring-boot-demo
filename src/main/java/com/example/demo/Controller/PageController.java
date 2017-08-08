package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Page")
public class PageController {
    @RequestMapping("page")
    public String page(ModelMap modelMap){
        modelMap.addAttribute("host","http://blog.didispace.com");
        return "index";
    }
}
