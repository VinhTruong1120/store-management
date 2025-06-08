package com.projects.cnpm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller // Đảm bảo có annotation @Controller
public class HomeController {

    @GetMapping("/") // Hoặc @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index.html") // Hoặc @RequestMapping("/")
    public String hrindex() {
        return "index";
    }
    @GetMapping("/goods.html")
    public String goods() {
        return "goods";
    }

    @GetMapping("/order.html")
    public String orders() {
        return "order";
    }


    @GetMapping("/after-sale.html")
    public String getMethodName() {
        return "after-sale";
    }
    
    @GetMapping("/staff.html")
    public String staff() {
        return "staff";
    }


}