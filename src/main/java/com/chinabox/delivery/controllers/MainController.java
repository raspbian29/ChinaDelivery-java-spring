package com.chinabox.delivery.controllers;

import com.chinabox.delivery.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MainController {

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        User userDto = new User();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("greeting", "Main page");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "about");
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "login");
        return "login";
    }


}
