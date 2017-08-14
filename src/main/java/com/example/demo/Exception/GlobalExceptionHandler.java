package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    //    //返回一个自定义的报错页面
//    public static final String DEFAULT_ERROR_VIEW="error";
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest httpServletRequest,Exception e) throws Exception{
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("exception",e);
//        modelAndView.addObject("url",httpServletRequest.getRequestURL());
//        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
//        return modelAndView;
//    }
    //返回一个自定义的报错Json
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map jsonErrorHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        Map map = new HashMap();
        if (e.getMessage() != null) map.put("message", e.getMessage());
        map.put("code", HttpStatus.CONTINUE);
        map.put("data", "No data");
        map.put("url", httpServletRequest.getRequestURL());
        return map;
    }
}
