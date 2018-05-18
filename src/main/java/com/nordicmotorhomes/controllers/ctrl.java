package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.IObjectRepository;
import com.nordicmotorhomes.database.StaffRepository;
import com.nordicmotorhomes.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ctrl {

    private ArrayList<Staff> staff = new ArrayList<>();
    private IObjectRepository iObjectRepository;

    public ctrl() {
        this.iObjectRepository = new StaffRepository();
        this.staff = iObjectRepository.readAll("staff");
    }

    @GetMapping("/")
    public String getIndex() {

        return "index";
    }

    @PostMapping("/")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password, ArrayList<Staff> staff) {

        boolean bool = iObjectRepository.readOne(username, password);

        if(bool) {
            return "redirect:/" + username;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("stf", staff);

        System.out.println(model);
        System.out.println(staff);

        return "admin";
    }

    @PostMapping("/admin")
    public String refreshAdmin() {
        return "redirect:/";
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
