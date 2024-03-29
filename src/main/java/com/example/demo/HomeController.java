package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
public class HomeController {
    @GetMapping("/loadform")
    public String index(Model model){

        //preload Today's Date for Application
        LocalDate today = LocalDate.now();
        model.addAttribute("date1", today);

        //preload Today's Time for Interview
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm a");
        LocalTime timeNow = LocalTime.now();

        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeString = timeNow.format(timeFormatter);
        model.addAttribute("time1", timeString);
        return "form";
    }

    @RequestMapping("/processform")
    public String loadFromPage(@RequestParam("date1Form") String date1Str,
                               @RequestParam("interview-time") String interviewTime,
                               Model model){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate myDate1 = LocalDate.parse(date1Str, dateFormatter);
        model.addAttribute("date1", myDate1);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try{
        LocalTime selectedTime = LocalTime.parse(interviewTime, timeFormatter);
        model.addAttribute("confirmedTime",selectedTime);
        return "confirm";
        } catch (DateTimeParseException e) {
            model.addAttribute("errorMessage", "you must enter a value for the time!");

            return "form";
        }
    }


}
