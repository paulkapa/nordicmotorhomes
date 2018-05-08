package com.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @GetMapping("/")
    public String index() {

        //UserRepository ur = new UserRepository();
        //System.out.println(ur.readAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(){

        //UserRepository ur = new UserRepository();
        //ur.create(new Person());
        return "index";
    }
}
