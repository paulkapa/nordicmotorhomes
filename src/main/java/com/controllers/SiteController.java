package com.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class SiteController {

    @GetMapping("/")
    public String index() {

        //UserRepository ur = new UserRepository();
        //System.out.println(ur.readAll());
        return "index";
    }

    @PostMapping("/")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        boolean bool = false; //iGameRepository.readOne(nickname, password);

        if(bool) {
            return "game";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/create")
    public String create(){

        //UserRepository ur = new UserRepository();
        //ur.create(new Person());
        return "index";
    }
}
