package com.controllers;


import com.database.StaffRepository;
import com.models.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class SiteController {

    private ArrayList<Staff> staff;
    private StaffRepository staffRepository;

    public SiteController() {
        this.staffRepository = new StaffRepository();
    }

    @GetMapping("/")
    public String index() {
        staff = staffRepository.readAll("staff");
        System.out.println(staff);
        return "index";
    }

    @PostMapping("/")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        boolean bool = staffRepository.readOne(username, password);

        if(bool) {
            return "index";
        } else {
            return "redirect:/";
        }
    }
/*
    @GetMapping("/game")
    public String create(){

        //UserRepository ur = new UserRepository();
        //ur.create(new Person());
        return "index";
    }*/
}
