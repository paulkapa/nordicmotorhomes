package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.IObjectRepository;
import com.nordicmotorhomes.database.StaffRepository;
import com.nordicmotorhomes.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ctrl {

    private ArrayList<Staff> staff;
    private IObjectRepository iObjectRepository;

    public ctrl() {
        this.iObjectRepository = new StaffRepository();
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("stf", iObjectRepository.readAll("staff"));
        return "index";
    }

    @PostMapping("/")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password, ArrayList<Staff> staff) {

        boolean bool = iObjectRepository.readOne(username, password);

        if(bool) {
            return username;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin")
    public String getAdminPage() {

        return "admin";
    }

    @GetMapping("/sales")
    public String getSalesPage() {

        return "sales";
    }

    @GetMapping("/cleaning")
    public String getCleaningPage() {

        return "cleaning";
    }

    @GetMapping("/mechanic")
    public String getMechanicPage() {

        return "mechanic";
    }

    @GetMapping("/bookkeeper")
    public String getBookkeeperPage() {

        return "bookkeeper";
    }
}
