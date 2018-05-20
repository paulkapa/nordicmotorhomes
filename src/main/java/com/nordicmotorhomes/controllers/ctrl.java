package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.*;
import com.nordicmotorhomes.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class ctrl {

    private static IObjectRepository staffRepository = new StaffRepository();
    private static IObjectRepository userRepository = new UserRepository();
    private static IObjectRepository bookingRepository = new BookingRepository();
    private static IObjectRepository motorhomeRepository = new MotorhomeRepository();
    private static IObjectRepository brandRepository = new BrandRepository();
    private static IObjectRepository modelRepository = new ModelRepository();
    private static IObjectRepository typeRepository = new TypeRepository();
    private static IObjectRepository repairRepository = new RepairRepository();

    private static ArrayList<Staff> staffList;
    private static ArrayList<User> userList;
    private static ArrayList<Booking> bookingList;
    private static ArrayList<Motorhome> motorhomeList;
    private static ArrayList<Brand> brandList;
    private static ArrayList<Modela> modelList;
    private static ArrayList<Type> typeList;
    private static ArrayList<Repair> repairList;

    public ctrl() {

        staffList = staffRepository.readAll("staff");
        userList = userRepository.readAll("users");
        bookingList = bookingRepository.readAll("mtrhms_bookings");
        motorhomeList = motorhomeRepository.readAll("mtrhms");
        brandList = brandRepository.readAll("brands");
        modelList = modelRepository.readAll("models");
        typeList = typeRepository.readAll("types");
        repairList = repairRepository.readAll("mtrhms_repairs");
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

        model.addAttribute("stf", staffList);
        model.addAttribute("usr", userList);
        model.addAttribute("bkn", bookingList);
        model.addAttribute("mtrhm", motorhomeList);
        model.addAttribute("brnd", brandList);
        model.addAttribute("mdl",modelList);
        model.addAttribute("type", typeList);
        model.addAttribute("repair", repairList);

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
    /*
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {

        Motorhome motorhome = motorhomeRepository.read(id);
        model.addAttribute("motorhome", motorhome);
        return "update";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute Motorhome motorhome) {
        motorhomeRepository.update("mtrhms", motorhome);
        return "redirect:/";
    }
    */
}
