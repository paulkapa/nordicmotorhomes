package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.*;
import com.nordicmotorhomes.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class ctrl {

    private int logInAccess;

    private static IObjectRepository staffRepository = new StaffRepository();
    private static IObjectRepository userRepository = new UserRepository();
    private static IObjectRepository bookingRepository = new BookingRepository();
    private static IObjectRepository motorhomeRepository = new MotorhomeRepository();
    private static IObjectRepository modelRepository = new ModelRepository();
    private static IObjectRepository repairRepository = new RepairRepository();
    private static IObjectRepository extraRepository = new ExtraRepository();

    private static ArrayList<Staff> staffList;
    private static ArrayList<User> userList;
    private static ArrayList<Booking> bookingList;
    private static ArrayList<Motorhome> motorhomeList;
    private static ArrayList<Modela> modelList;
    private static ArrayList<Repair> repairList;
    private static ArrayList<Extra> extraList;

    public ctrl() {

        staffList = staffRepository.readAll("staff");
        userList = userRepository.readAll("users");
        bookingList = bookingRepository.readAll("mtrhms_bookings");
        motorhomeList = motorhomeRepository.readAll("mtrhms");
        modelList = modelRepository.readAll("models");
        repairList = repairRepository.readAll("mtrhms_repairs");
        extraList = extraRepository.readAll("extras");
    }

    @GetMapping("/")
    public String getIndex() {

        logInAccess = 0;

        return "index";
    }

    @PostMapping("/")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        boolean bool = staffRepository.readOne("staff", username, password);

        if (bool) {

            logInAccess = StaffRepository.checkLogin(username);

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
        model.addAttribute("mdl", modelList);
        model.addAttribute("repair", repairList);
        model.addAttribute("extra", extraList);

        if (logInAccess == 1) {
            return "admin";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/sales")
    public String getSalesPage(Model model) {

        model.addAttribute("usr", userList);
        model.addAttribute("bkn", bookingList);
        model.addAttribute("mtrhm", motorhomeList);
        model.addAttribute("extra", extraList);

        if (logInAccess == 2) {
            return "sales";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/cleaning")
    public String getCleaningPage(Model model) {

        model.addAttribute("bkn", bookingList);
        model.addAttribute("mtrhm", motorhomeList);

        if (logInAccess == 3) {
            return "cleaning";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/mechanic")
    public String getMechanicPage(Model model) {

        model.addAttribute("mtrhm", motorhomeList);
        model.addAttribute("repair", repairList);

        if (logInAccess == 4) {
            return "mechanic";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/bookkeeper")
    public String getBookkeeperPage(Model model) {

        model.addAttribute("usr", userList);
        model.addAttribute("bkn", bookingList);
        model.addAttribute("mtrhm", motorhomeList);
        model.addAttribute("extra", extraList);


        if (logInAccess == 5) {
            return "bookkeeper";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/addExtras")
    public String addExtras() {
        if(logInAccess!=0) {
            return "addExtras";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addExtras")
    public String addExtras(@ModelAttribute Extra extra) {

        try {
            extraRepository.create("extras", extra);
            extra.setId(extraList.size()+1);
            extraList.add(extra);
        } catch (Exception e) {
        }

        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 2:
                return "redirect:/sales";
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/addRepairs")
    public String addRepairs() {
        if(logInAccess!=0) {
            return "addRepairs";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addRepairs")
    public String addRepairs(@ModelAttribute Repair repair) {

        try {
            extraRepository.create("mtrhms_repairs", repair);
            repair.setRepairId(repairList.size()+1);
            repairList.add(repair);
        } catch (Exception e) {
        }

        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 4:
                return "redirect:/mechanic";
        }

        return "redirect:/";
    }

}