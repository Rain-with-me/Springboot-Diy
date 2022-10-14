package com.lu.edu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatis自动生成
 * @since 2022-10-13
 */
@Controller
public class ScySecurityController {
    @RequestMapping("/")
    public String test(){
        return "index";
    }
}

