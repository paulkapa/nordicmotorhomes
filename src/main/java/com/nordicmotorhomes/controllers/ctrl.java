package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.*;
import com.nordicmotorhomes.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.sql.Date;
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
    private static ArrayList<Repair> repairList;
    private static ArrayList<Extra> extraList;

    public ctrl() {

        staffList = staffRepository.readAll("staff");
        userList = userRepository.readAll("users");
        bookingList = bookingRepository.readAll("mtrhms_bookings");
        motorhomeList = motorhomeRepository.readAll("mtrhms");
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

        model.addAttribute("bkn", bookingList);
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
        if(logInAccess == 1 || logInAccess == 2 || logInAccess == 5) {
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
            extraList = extraRepository.readAll("extra");
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
    public String addRepairs(Model model) {
        if(logInAccess == 1 || logInAccess == 4) {
            model.addAttribute("mtrhm", motorhomeList);
            return "addRepairs";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addRepairs")
    public String addRepairs(@ModelAttribute Repair repair) {

        try {
            repairRepository.create("mtrhms_repairs", repair);
            repairList = repairRepository.readAll("mtrhms_repairs");
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

    @GetMapping("/addBookings")
    public String addBookings(Model model) {
        if(logInAccess == 1 || logInAccess == 2 || logInAccess == 3 || logInAccess == 5) {
            model.addAttribute("mtrhm", motorhomeList);
            model.addAttribute("usr", userList);
            return "addBookings";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addBookings")
    public String addBookings(@ModelAttribute Booking booking) {

        try {
            bookingRepository.create("mtrhms_bookings", booking);
            bookingList = bookingRepository.readAll("mtrhms_bookings");
        } catch (Exception e) {
        }

        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 2:
                return "redirect:/sales";
            case 3:
                return "redirect:/cleaning";
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/addRental")
    public String addRental(Model model) {
        if(logInAccess == 1 || logInAccess == 2 || logInAccess == 3 || logInAccess == 5) {
            model.addAttribute("mtrhm", motorhomeList);
            model.addAttribute("usr", userList);
            return "addRental";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addRental")
    public String addRental(@ModelAttribute Booking booking) {

        try {
            bookingRepository.create("mtrhms_bookings", booking);
            bookingList = bookingRepository.readAll("mtrhms_bookings");
        } catch (Exception e) {
        }

        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 2:
                return "redirect:/sales";
            case 3:
                return "redirect:/cleaning";
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/addMotorhomes")
    public String addMotorhomes() {
        if(logInAccess == 1 || logInAccess == 2 || logInAccess == 3 || logInAccess == 4 || logInAccess == 5) {
            return "addMotorhomes";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addMotorhomes")
    public String addMotorhomes(@ModelAttribute Motorhome motorhome) {

        try {
            motorhomeRepository.create("mtrhms", motorhome);
            motorhomeList = motorhomeRepository.readAll("mtrhms");
        } catch (Exception e) {

        }

        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 2:
                return "redirect:/sales";
            case 3:
                return "redirect:/cleaning";
            case 4:
                return "redirect:/mechanic";
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/addUsers")
    public String addUsers() {
        if(logInAccess == 1 || logInAccess == 2 || logInAccess == 5) {
            return "addUsers";
        }
        else {
            return "redirect:/";
        }

    }

    @PostMapping("/addUsers")
    public String addUsers(@ModelAttribute User user) {

        try {
            userRepository.create("users", user);
            userList = userRepository.readAll("user");
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

    @GetMapping("/calculatePrice")
    public String calculatePrice() {
        if(logInAccess == 2) {
            return "calculatePrice";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/calculatePrice")
    public String calculatePrice(@RequestParam("id") int id, Model model) {
        double totalPrice = 0;

        Booking booking = (Booking) bookingRepository.read("mtrhms_bookings", "pKey_bookingId", String.valueOf(id));

        long diff = (booking.getStartDate().getTime()-booking.getCancellationDate().getTime()) / 86400000;

        if(booking.getIsCancelled() == 1) {

            if(diff>=0 && diff <15) {
                totalPrice = 0.95 * booking.getPpd();
            } else if(diff>=15 && diff<=49) {
                totalPrice = 0.5 * booking.getPpd();
            } else if(diff>49) {
                totalPrice = 0.2 * booking.getPpd();
            }

            if(totalPrice < 200) {
                totalPrice = 200;
            }

        } else if(booking.getStartDate().getMonth() >= 1 && booking.getStartDate().getMonth() < 4) {

            totalPrice = booking.getPpd() + booking.getPickUpDistance() * 0.7 + booking.getDropOffDistance() * 0.7;

            if(booking.getDropOffKmNr()>400)
                totalPrice = totalPrice + booking.getDropOffKmNr() - 400;

        } else if(booking.getStartDate().getMonth() >= 4 && booking.getStartDate().getMonth() < 8) {

            totalPrice = booking.getPpd() * 1.3 + booking.getPickUpDistance() * 0.7 + booking.getDropOffDistance() * 0.7;

            if(booking.getDropOffKmNr()>400)
                totalPrice = totalPrice + booking.getDropOffKmNr() - 400;

        } else if(booking.getStartDate().getMonth() >=8 && booking.getStartDate().getMonth() <= 12) {

            totalPrice = booking.getPpd() * 1.6 + booking.getPickUpDistance() * 0.7 + booking.getDropOffDistance() * 0.7;

            if(booking.getDropOffKmNr()>400)
                totalPrice = totalPrice + booking.getDropOffKmNr() - 400;

        }

        booking.setTotalPrice((int) totalPrice);

        model.addAttribute("bkng", booking);

        bookingRepository.update("mtrhms_booking", booking);

        bookingList = bookingRepository.readAll("mtrhms_booking");

        switch (logInAccess) {
            case 2:
                return "redirect:/sales";
        }

        return "redirect:/";
    }

}