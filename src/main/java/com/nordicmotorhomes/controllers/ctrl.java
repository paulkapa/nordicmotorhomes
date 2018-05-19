package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.IObjectRepository;
import com.nordicmotorhomes.database.StaffRepository;
import com.nordicmotorhomes.database.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ctrl {

    private IObjectRepository staffRepository;
    private IObjectRepository userRepository;

    public ctrl() {
        this.staffRepository = new StaffRepository();
        this.userRepository = new UserRepository();
    }

    @GetMapping("/")
    public String getIndex() {

        return "index";
    }

    @PostMapping("/")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        boolean bool = staffRepository.readOne("staff", username, password);

        if(bool) {
            return "redirect:/" + username;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {

        model.addAttribute("stf", staffRepository.readAll("staff"));
        model.addAttribute("usr", userRepository.readAll("users"));

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
