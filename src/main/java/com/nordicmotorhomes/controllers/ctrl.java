package com.nordicmotorhomes.controllers;

import com.nordicmotorhomes.database.*;
import com.nordicmotorhomes.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;


@Controller
public class ctrl {

    private int logInAccess;

    private static IObjectRepository staffRepository = new StaffRepository();
    private static IObjectRepository userRepository = new UserRepository();
    private static IObjectRepository bookingRepository = new BookingRepository();
    private static IObjectRepository motorhomeRepository = new MotorhomeRepository();
    private static IObjectRepository repairRepository = new RepairRepository();
    private static IObjectRepository extraRepository = new ExtraRepository();
    private static IObjectRepository rentalRepository = new RentalRepository();

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
            e.printStackTrace();
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
    public String addRepairs(@ModelAttribute Repair repair, @ModelAttribute Motorhome motorhome) {

        repairRepository.create("mtrhms_repairs", repair);
        repairList = repairRepository.readAll("mtrhms_repairs");

        switch (logInAccess) {

            case 1:
                return "redirect:/admin";
            case 4:
                return "redirect:/mechanic";
        }

        return "redirect:/";
    }

    @GetMapping("/updateRepairs")
    public String updateRepairs(@RequestParam("id") int id, Model model) {
        if (logInAccess == 1 || logInAccess == 4) {
            Repair repair = (Repair) repairRepository.readId(id);
            model.addAttribute("repair", repair);
            return "updateRepairs";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateRepairs")
    public String updateRepairs(@ModelAttribute Repair repair) {
        repairRepository.update("mtrhms_repair", repair);
        repairList = repairRepository.readAll("mtrhms_repair");
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
        if (logInAccess == 1 || logInAccess == 5) {
            model.addAttribute("mtrhm", motorhomeList);
            model.addAttribute("usr", userList);
            model.addAttribute("bookings", bookingList);
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
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/updateBookings")
    public String updateBookings(@RequestParam("id") int id, Model model) {

        if (logInAccess == 1 || logInAccess == 2 || logInAccess == 5) {
            Booking booking = (Booking) bookingRepository.readId(id);
            model.addAttribute("booking", booking);
            return "updateBookings";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateBookings")
    public String updateBookings(@ModelAttribute Booking booking) {

        bookingRepository.update("mtrhms_bookings", booking);
        bookingList = bookingRepository.readAll("mtrhms_bookings");


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

    @GetMapping("printContract")
    public String printContract(@RequestParam("id") int id, Model model) {

        if (logInAccess == 2) {

            Booking booking = (Booking) bookingRepository.readId(id);
            model.addAttribute("booking", booking);
            model.addAttribute("user", userList.get(booking.getUserId() - 1));
            model.addAttribute("motorhome", motorhomeList.get(booking.getUserId()));
            return "printContract";
        }
        return "redirect:/";
    }


    @GetMapping("/addRental")
    public String addRental(Model model) {
        if (logInAccess == 1 || logInAccess == 5) {
            model.addAttribute("mtrhm", motorhomeList);
            model.addAttribute("rental", bookingList);
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
            rentalRepository.create("mtrhms_bookings", booking);
            bookingList = bookingRepository.readAll("mtrhms_bookings");
        } catch (Exception e) {
        }

        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/addMotorhomes")
    public String addMotorhomes() {
        if (logInAccess == 1 || logInAccess == 4 || logInAccess == 5) {
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
            case 4:
                return "redirect:/mechanic";
            case 5:
                return "redirect:/bookkeeper";
        }

        return "redirect:/";
    }

    @GetMapping("/updateMotorhomes")
    public String updateMotorhomes(@RequestParam("id") int id, Model model) {
        if (logInAccess == 1 || logInAccess == 2 || logInAccess == 4 || logInAccess == 5) {
            Motorhome motorhome = (Motorhome) motorhomeRepository.readId(id);
            model.addAttribute("motorhome", motorhome);
            return "updateMotorhomes";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateMotorhomes")
    public String updateMotorhomes(@ModelAttribute Motorhome motorhome) {
        motorhomeRepository.update("mtrhms", motorhome);
        motorhomeList = motorhomeRepository.readAll("mtrhms");
        switch (logInAccess) {
            case 1:
                return "redirect:/admin";
            case 2:
                return "redirect:/sales";
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

    // we decided that on average, a motorhome consumes 10 liters per 100 kms
    // the 70 euros additional fee will be added randomly, as we can't actually take the data
    // from the customer to see if the tank was full or not by the dropoff
    @GetMapping("/calculatePrice")
    public String calculatePrice(@RequestParam("id") int id, Model model) {
        if (logInAccess == 2) {
            Booking booking = (Booking) bookingRepository.readId(id);
            model.addAttribute("booking", booking);

            int pricePerDay = booking.getPpd();
            double totalPrice = 0;
            long diffCancelation = 0;
            long diffRental = 0;


            diffCancelation = (Date.valueOf(booking.getStartDate()).getTime() - Date.valueOf(booking.getCancellationDate()).getTime()) / 86400000;
            diffRental = (Date.valueOf(booking.getEndDate()).getTime() - Date.valueOf(booking.getStartDate()).getTime()) / 86400000;
            diffRental++;
            if (booking.getIsCancelled() == 1) {
                if (totalPrice < 200) {
                    totalPrice = 200;
                }

                if (diffCancelation >= 0 && diffCancelation < 15) {
                    totalPrice += 0.95 * booking.getPpd();
                } else if (diffCancelation >= 15 && diffCancelation <= 49) {
                    totalPrice += 0.5 * booking.getPpd();
                } else if (diffCancelation > 49) {
                    totalPrice += 0.2 * booking.getPpd();
                }

            } else if (Date.valueOf(booking.getStartDate()).getMonth() >= 1 && Date.valueOf(booking.getStartDate()).getMonth() < 4) {
                // price stays the same in this season
                pricePerDay = booking.getPpd();

            } else if (Date.valueOf(booking.getStartDate()).getMonth() >= 4 && Date.valueOf(booking.getStartDate()).getMonth() < 8) {
                pricePerDay += (int) (booking.getPpd() * 0.3);


            } else if (Date.valueOf(booking.getStartDate()).getMonth() >= 8 && Date.valueOf(booking.getStartDate()).getMonth() <= 12) {
                pricePerDay += (int) (booking.getPpd() * 0.6);
            }

            int averageKilometers = (int) (booking.getDropOffKmNr() / diffRental);

            if (averageKilometers > 400) {
                totalPrice += averageKilometers - 400;
            } else {
                totalPrice += diffRental * pricePerDay;
            }

            Random rand = new Random();
            int fuel = rand.nextInt(2);
            if (fuel == 1) {
                totalPrice += 70;
            }

            totalPrice += booking.getExtrasPrice();
            totalPrice += booking.getPickUpDistance() * 0.7 + booking.getDropOffDistance() * 0.7;
            booking.setTotalPrice((int) totalPrice);
            bookingRepository.update("mtrhms_booking", booking);
            bookingList = bookingRepository.readAll("mtrhms_booking");
            return "calculatePrice";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/updateUsers")
    public String updateUsers(@RequestParam("id") int id, Model model) {

        if (logInAccess == 1 || logInAccess == 2 || logInAccess == 5) {
            User user = (User) userRepository.readId(id);
            model.addAttribute("user", user);
            return "updateUsers";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/updateUsers")
    public String updateUsers(@ModelAttribute User user) {

        userRepository.update("users", user);
        userList = userRepository.readAll("users");
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


}