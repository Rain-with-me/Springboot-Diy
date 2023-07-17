package com.lu.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 雨同我
 * @Description:
 * @DateTime: 2023/7/17 14:46
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}
